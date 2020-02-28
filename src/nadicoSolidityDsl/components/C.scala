package nadicoSolidityDsl.components

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
 * This class describes the Conditions component of nAdico statements.
 * 
 */
case class C(conditions: ConditionExpression) extends ConditionExpression{
  
}

/**
 * Shortcut for individual condition creation
 */
object C {

  def apply(constraint: String): C = {
    new C(Condition(constraint))
  }
  
  def apply(qualifier: Any, constraint: Any): C = {
    new C(Condition(null, qualifier, constraint))
  }
  
  def apply(obj: Any, qualifier: Any, constraint: Any): C = {
    new C(Condition(obj, qualifier, constraint))
  }
  
  def IF(obj: Any, qualifier: Any, constraint: Any): C = {
    new C(Condition(obj, qualifier, constraint))
  }
  
}
