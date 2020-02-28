package nadicoSolidityDsl.codeBuilder

import nadicoSolidityDsl.NAdicoStatement
import nadicoSolidityDsl.components.I
import nadicoSolidityDsl.components.C
import nadicoSolidityDsl.NAdicoExpression
import nadicoSolidityDsl.NAdicoCombination
import nadicoSolidityDsl.components.ConditionCombination
import nadicoSolidityDsl.components.Condition
import nadicoSolidityDsl.components.ConditionExpression

/**
 * This file is part of the nADICO-Solidity DSL Implementation for the 
 * automated generation of smart contracts.
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
 * This class contains the code builder to generate Solidity contract skeletons.
 * For example usage, please refer to the accompanying worksheet examples.
 * 
 */
class CodeBuilder {
  
  val modifiers = collection.mutable.Map[String, String]()
  val functions = collection.mutable.Map[String, String]()
  val lb = sys.props("line.separator")
  val dlb = lb + lb
  
  def buildModifier(name: String, condition: ConditionExpression, orElse: NAdicoExpression, store: Boolean): String = {
    
    condition match {
      case _: C =>
              buildModifier(
                  name, 
                  condition.asInstanceOf[C].conditions, 
                  orElse, 
                  store)
      case _: ConditionCombination => 
              buildModifier(
                  name, 
                  condition.asInstanceOf[ConditionCombination].left, 
                  orElse, 
                  store)
              buildModifier(
                  name, 
                  condition.asInstanceOf[ConditionCombination].right, 
                  orElse, 
                  store)
      case _: Condition =>
              buildIndividualModifier(name, condition, orElse, store)
    }
  }
  
  private def buildIndividualModifier(name: String, condition: ConditionExpression, orElse: NAdicoExpression, store: Boolean): String = {
    var key = name
    if(name == null){
      key = condition.asInstanceOf[Condition].toString()
    } else {
      key = name
    }
    val modifier = "  modifier " + key + "() {" + lb + 
      "    // TODO: Check the condition" + lb + 
      "    if (! " + condition + ") " + lb + 
      "      throw; " + lb + 
      "      _ " + lb + 
      "  }"
    if (store) { 
      modifiers.put(key, modifier)
    }
    modifier
  }
  
  def buildFunction(name: String, aim: I, store: Boolean): String = {
    var key = name
    if(name == null){
      key = aim.action
    } else {
      key = name
    }
    val function = 
      // Write todo comment if parameters specified
      (if (aim.obj != null || aim.target != null) 
        ("  // TODO: Doublecheck parameter type definitions for function " + key + lb) else "") + 
      "  function " + key + "(" + 
      // Object and target parameters 
        (if (aim.obj != null && aim.target != null) 
           (aim.obj.toSolidityString() + ", " + aim.target.toSolidityString()) else 
               (if (aim.obj != null) (aim.obj.toSolidityString()) else 
               (if (aim.target != null) aim.target.toSolidityString() else ""))) + 
        ") " + modifiers.keySet.mkString(" ") + " {" + lb +
      // Write comment in code 
      "    // TODO: Implement code to " + aim.action +  
         (if (aim.obj != null ) " " + aim.obj else "") +  
         (if (aim.obj != null && aim.target != null) (" for target " + aim.target) else 
             if(aim.target != null) aim.target else "") + lb +
      "  }"
    if (store) {
      functions.put(key, function)
    }
    function
  }
  
  def addToContract(adico: NAdicoExpression): Boolean = {
    print("Added new statement.")
    adico match {
      case _: NAdicoCombination => //print("AdicoCombo it is.")
              val inst = adico.asInstanceOf[NAdicoCombination]
              addToContract(inst.left)
              addToContract(inst.right)
      case _: NAdicoStatement => //print("Adico it is.")
              val inst = adico.asInstanceOf[NAdicoStatement]
              buildModifier(null, inst.conditions, inst.orElse, true)
              buildFunction(null, inst.aim, true)
    }
    true
  }
  
  def generateContract(name: String): String = {
    var contract = "contract " + name + " {" + dlb
    
    // state variable of creator
    contract += "  address owner;" + dlb
    
    // Add constructor with creator's address
    contract += "  function " + name + "() {" + lb +
      "    owner = msg.sender;" + lb + 
      "  }" + dlb
    
    modifiers.foreach(contract += _._2 + dlb)
    functions.foreach(contract += _._2 + dlb)
    
    // Capture malformed payload
    contract +=
      "  // Capture malformed payload" + lb +
      "  function() {" + lb +
      "    throw;" + lb +
      "  }" + dlb
    
    // Add kill capability (and check for authorisation)
    contract += 
      "  // Destroys contract, but only if called by original creator of contract" + lb + 
      "  function kill() {" + lb +
      "    if (msg.sender == owner)" + lb + 
      "      selfdestruct(owner);" + lb + 
      "  }" + dlb
    
    contract + "}" + lb
  }
  
}