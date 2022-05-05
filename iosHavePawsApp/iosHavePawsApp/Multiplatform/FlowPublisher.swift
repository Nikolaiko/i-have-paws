import Foundation
import Combine
import shared

public struct FlowPublisher<T: AnyObject>: Publisher {
    public typealias Output = T
    public typealias Failure = Never

    private let flowWrapper: AnyFlow<Output>
    public init(kotlinFlow: AnyFlow<Output>) {
        flowWrapper = kotlinFlow
    }

    public func receive<S: Subscriber>(subscriber: S) where S.Input == Output, S.Failure == Failure {
        let subscription = FlowSubscription(
            wrapper: flowWrapper,
            subscriber: subscriber
        )
        subscriber.receive(subscription: subscription)
    }

    final class FlowSubscription<S: Subscriber>: Subscription where S.Input == Output, S.Failure == Failure {
        private var subscriber: S?
        private var job: shared.Cancellable?

        private let wrapper: AnyFlow<Output>

        init(wrapper: AnyFlow<Output>, subscriber: S) {
            self.wrapper = wrapper
            self.subscriber = subscriber

            job = wrapper.collect(onEach: { data in
                _ = subscriber.receive(data!)
            }, onComplete: { error in
                if let error = error {
                    debugPrint(error.description())
                }
                subscriber.receive(completion: .finished)
            })
        }

        func cancel() {
            subscriber = nil
            job?.cancel()
        }

        func request(_ demand: Subscribers.Demand) {}
    }
}
