import SwiftUI

extension View {
  public func alert(isPresented: Binding<Bool>, _ model: UIAlertModel) -> some View {
    return AlertWrapper(isPresented: isPresented, model: model, content: self)
  }
}
