package akka.actor

import scala.reflect.ClassTag

final class Props(clazz: Class[_ <: Actor], creator: () => Actor) {
  private[akka] def newActor(): Actor =
    creator()
}

object Props {
  def apply[A <: Actor : ClassTag](creator: => A): Props =
    apply(implicitly[ClassTag[A]].runtimeClass.asInstanceOf[Class[_ <: Actor]],
        () => creator)

  def apply(clazz: Class[_ <: Actor], creator: () => Actor): Props =
    new Props(clazz, creator)
}
