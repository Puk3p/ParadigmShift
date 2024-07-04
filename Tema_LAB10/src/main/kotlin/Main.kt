package puk3p.tuiasi.ro
import kotlinx.coroutines.*

interface AbstractFactory {
    fun getHandler(handlerType: String): Handler
}

interface Handler {
    var nextUp: Handler?
    var nextDown: Handler?

    suspend fun handleRequest(messageToBeProcessed: String)
}

class EliteFactory : AbstractFactory {
    override fun getHandler(handlerType: String): Handler {
        return when (handlerType) {
            "CEO" -> CEOHandler()
            "Executive" -> ExecutiveHandler()
            "Manager" -> ManagerHandler()
            else -> throw IllegalArgumentException("No handler")
        }
    }
}

class HappyWorkerFactory : AbstractFactory {
    override fun getHandler(handlerType: String): Handler {
        return when (handlerType) {
            "Worker" -> HappyWorkerHandler()
            else -> throw IllegalArgumentException("No handler")
        }
    }
}

class CEOHandler : Handler {
    override var nextUp: Handler? = null
    override var nextDown: Handler? = null

    override suspend fun handleRequest(messageToBeProcessed: String) {
        if (messageToBeProcessed.startsWith("CEO")) {
            println("Processing by CEO: $messageToBeProcessed")
            delay(1000)
            nextDown?.handleRequest("Response - Processed by CEO")
        } else {
            nextUp?.handleRequest(messageToBeProcessed)
        }
    }
}

class ExecutiveHandler : Handler {
    override var nextUp: Handler? = null
    override var nextDown: Handler? = null

    override suspend fun handleRequest(messageToBeProcessed: String) {
        if (messageToBeProcessed.startsWith("Executive")) {
            println("Processing by Executive: $messageToBeProcessed")
            delay(500)
            nextDown?.handleRequest("Response - Processed by Executive")
        } else {
            nextUp?.handleRequest(messageToBeProcessed)
            //nextUp?.handleRequest(messageToBeProcessed)
        }
    }
}

class ManagerHandler : Handler {
    override var nextUp: Handler? = null
    override var nextDown: Handler? = null

    override suspend fun handleRequest(messageToBeProcessed: String) {
        if (messageToBeProcessed.startsWith("Manager")) {
            println("Processing by Manager: $messageToBeProcessed")
            delay(800)
            nextDown?.handleRequest("Response - Processed by Manager")
        } else {
            nextUp?.handleRequest(messageToBeProcessed)
        }
    }
}

class HappyWorkerHandler : Handler {
    override var nextUp: Handler? = null
    override var nextDown: Handler? = null

    override suspend fun handleRequest(messageToBeProcessed: String) {
        if (messageToBeProcessed.startsWith("Worker")) {
            println("Processing by Worker: $messageToBeProcessed")
            delay(300)
            nextDown?.handleRequest("Response - Processed by Worker")
        } else {
            println("Worker cannot handle: $messageToBeProcessed")
        }
    }
}

fun main() = runBlocking {
    val eliteFactory = EliteFactory()
    val happyWorkerFactory = HappyWorkerFactory()

    val ceo = eliteFactory.getHandler("CEO") as CEOHandler
    val executive = eliteFactory.getHandler("Executive") as ExecutiveHandler
    val manager = eliteFactory.getHandler("Manager") as ManagerHandler
    val worker = happyWorkerFactory.getHandler("Worker") as HappyWorkerHandler

    ceo.nextUp = executive
    executive.nextUp = manager
    manager.nextUp = worker

    worker.nextDown = manager
    manager.nextDown = executive
    executive.nextDown = ceo


    ceo.handleRequest("Executive - Need to be processed")
    ceo.handleRequest("Worker - Need to be processed")
    ceo.handleRequest("Manager - Need processing")
}
