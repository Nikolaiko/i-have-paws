import Foundation
import shared

typealias GroupCallback = (shared.Group) -> Void
typealias BoolCallback = (Bool) -> Void
typealias StringCallback = (String) -> Void
typealias ErrorCallback = (AppError?) -> Void

typealias FlowCompleteCallback = (KotlinUnit?, Error?) -> Void
