import nadicoSolidityDsl._
import nadicoSolidityDsl.components._
import nadicoSolidityDsl.components.C.IF
import nadicoSolidityDsl.enums.Deontic._
import nadicoSolidityDsl.codeBuilder.CodeBuilder
import nadicoSolidityDsl.enums.Operator
import java.io._

/**
 * This file is part of the nADICO-Solidity DSL Implementation for the
 * Automated Generation of Smart Contracts.
 *
 * Publication:
 * C. K. Frantz and M. Nowostawski, "From Institutions to Code:
 * Towards Automated Generation of Smart Contracts," 2016 IEEE
 * 1st International Workshops on Foundations and Applications
 * of Self* Systems (FAS*W), Augsburg, 2016, pp. 210-215.
 * https://doi.org/10.1109/FAS-W.2016.53
 *
 * @author <a href="mailto:cf@christopherfrantz.org">Christopher K. Frantz</a>
 * @version 0.1
 *
 * This Scala worksheet shows examples for generated nADICO-Solidity smart contracts.
 *
 */
object Sheet {
  println("This Scala worksheet contains examples of nADICO-Solidity-generated smart contracts.")
 
 /**
  * The first example from the paper (see above) is a voting contract.
  */
 
 // Step 1: Compose individual rules as nADICO statements
 
 var rule10 = NAdico(
  		A("People"),
  		D(may),
  		I("cast", onObject("vote", "string"), onTarget("candidate")),
  		C(IF("voter", "is", "registered") AND IF("vote", "before", "deadline")))
  
 var rule11 = NAdico(
  		A("People"),
  		D(may),
  		I("cast", onObject("vote", "string"), onTarget("candidate")),
  		C("only", "once"))
 
 
 var rule12 = NAdico(
  		A("System"),
  		D(must),
  		I("notify", onObject("vote count"), onTarget("contract", "address")),
  		C(IF("vote count", Operator.>, "100")))
 
 // Step 2: Add those rules to the code builder
 
 var cb1 = new CodeBuilder()
 cb1.addToContract(rule10)
 cb1.addToContract(rule11)
 cb1.addToContract(rule12)
 
 // Step 3: Generate the contract
 
 var votingContract = cb1.generateContract("AdvancedVotingSystem")

 // Step 4: Write contract to file

 new PrintWriter("EscrowContract.js") { write(escrowContract); close }
 
 /////////////////////////////////////////////////////////////////////

 /**
  * The second example from the paper (link in file documentation) is an escrow contract.
  */
 
 // Step 1: Compose individual rules as nADICO statements
 
 var rule20 = NAdicoStatement(
  		A("Buyer"),
  		D(must),
  		I("pay", onObject("funds")),
  		C("before", "deadline"),
  		(NAdico(
  			A("System"),
  			D(must),
  			I("release", onObject("objectOfInterest"), onTarget("Seller", "address")))
  			AND
  			NAdico(
  			A("System"),
  			D(must),
  			I("send", onObject("funds"), onTarget("Buyer", "address"))))
  		)
 
 
 var rule21 = NAdico(
  		A("System"),
  		D(must),
  		I("notify", null, onTarget("Seller", "address")),
  		C(IF("msg.value", Operator.>=, "price"))
  		)
 
 var rule22 = NAdico(
  		A("System"),
  		D(must),
  		I("send", onObject("funds"), onTarget("Seller", "address")),
  		C(IF("msg.value", Operator.>=, "price"))
  		)

 var rule23 = NAdico(
  		A("System"),
  		D(must),
  		I("release", onObject("objectOfInterest"), onTarget("Buyer", "address")),
  		C(IF("msg.value", Operator.>=, "price"))
  		)

 // Step 2: Add those rules to the code builder
 
 var cb2 = new CodeBuilder()
 cb2.addToContract(rule20)
 cb2.addToContract(rule21)
 cb2.addToContract(rule22)
 
 // Step 3: Generate the contract
 
 var escrowContract = cb2.generateContract("Escrow")
 
 // Step 4: Write contract to file
   
 new PrintWriter("EscrowContract.js") { write(escrowContract); close }

}