import android.view.View
import android.os.{Bundle, ResultReceiver}


object AndroidImplicits {
  implicit def toListener[F](f: View => F) =
    new View.OnClickListener {
      def onClick(view: View) {
        f(view)
      }
    }

  implicit def toRunnable[F](f: => F): Runnable =
    new Runnable() {
      def run() = f
    }

  implicit def functionToResultReceicer[F](f:(Int,Bundle) => F): ResultReceiver = new ResultReceiver(null) {
    override def onReceiveResult(resultCode: Int, resultData: Bundle) {
      f(resultCode, resultData)
    }
  }
}
