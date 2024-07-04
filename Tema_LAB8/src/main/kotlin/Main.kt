class AndGate(private val inputs: List<Boolean>) {
    fun computeOutput(): Boolean {
        return inputs.all { it }
    }
}

class AndGateBuilder {
    private val inputs = mutableListOf<Boolean>()

    fun addInput(input: Boolean): AndGateBuilder {
        inputs.add(input)
        return this
    }

    fun build(): AndGate {
        return AndGate(inputs)
    }
}

fun main() {
    val twoInputGateBuilder = AndGateBuilder().addInput(true).addInput(false)
    val twoInputGate = twoInputGateBuilder.build()
    println("Iesire pentru 2: ${twoInputGate.computeOutput()}")

    val threeInputGate = AndGateBuilder().addInput(true).addInput(true).addInput(true).build()
    println("Iesire pentru 3: ${threeInputGate.computeOutput()}")

    val fourInputGate = AndGateBuilder().addInput(true).addInput(true).addInput(true).addInput(true).build()
    println("Iesire pentru 4: ${fourInputGate.computeOutput()}")

    val sixInputGate = AndGateBuilder().addInput(true).addInput(false).addInput(true).addInput(false)
        .addInput(true).addInput(true).build()
    println("Iesire pentru 6: ${sixInputGate.computeOutput()}")

    val eightInputGate = AndGateBuilder().addInput(true).addInput(true).addInput(true).addInput(true)
        .addInput(true).addInput(true).addInput(true).addInput(true).build()
    println("Iesire pentru 8: ${eightInputGate.computeOutput()}")
}
