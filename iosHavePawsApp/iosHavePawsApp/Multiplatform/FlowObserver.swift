import Foundation
import shared

typealias FlowCollector = Kotlinx_coroutines_coreFlowCollector

class FlowObserver: FlowCollector {
    let callback: (Any?) -> Void

    init(callback: @escaping (Any?) -> Void) {
        self.callback = callback
    }

    func emit(
        value: Any?,
        completionHandler: @escaping FlowCompleteCallback = { _, _ in   }
    ) {
        callback(value)
        completionHandler(KotlinUnit(), nil)
    }
}
