import Foundation
import SwiftUI

struct Toast: ViewModifier {
  
    let message: String
    @Binding var isShowing: Bool
    let config: UIToastModel

  func body(content: Content) -> some View {
    ZStack {
      content
      toastView
    }
  }

  private var toastView: some View {
    VStack {
      Spacer()
      if isShowing {
        Group {
          Text(message)
            .multilineTextAlignment(.center)
            .foregroundColor(config.textColor)
            .font(config.font)
            .padding(8)
        }
        .background(config.backgroundColor)
        .cornerRadius(8)
        .onTapGesture {
          isShowing = false
        }
        .onAppear {
          DispatchQueue.main.asyncAfter(deadline: .now() + config.duration) {
            isShowing = false
          }
        }
      }
    }
    .padding(.horizontal, 16)
    .padding(.bottom, 18)
    .animation(config.animation, value: isShowing)
    .transition(config.transition)
  }
}
