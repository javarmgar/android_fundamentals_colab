package patterns.observer

class ConcreteObservee:Observee {

    override val observers: MutableList<Observer> = mutableListOf()
    var value:Persona = Persona("Javier Armenta", 22)
    override fun addObserver(observer: Observer) {
        observers.add(observer)
    }

    override fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    override fun notifyObservers() {
        observers.forEach {
            it.update(value)
        }
    }

    fun updateAge(){
        value.age++
        notifyObservers()
    }
}