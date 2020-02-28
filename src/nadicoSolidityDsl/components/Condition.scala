package nadicoSolidityDsl.components

import nadicoSolidityDsl.enums.Combinator
import nadicoSolidityDsl.enums.Operator

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
 * This class describes an individual condition as part of the Conditions 
 * component of nAdico statements.
 * 
 */
case class Condition(obj: Any, qualifier: Any, constraint: Any) extends ConditionExpression {

  /**
   * Single term condition - constraint
   */
  def this(constraint: Any) = this(null, null, constraint)
  
  /**
   * Qualifying the aim (e.g.~'more than', 5)
   */
  def this(qualifier: Any, constraint: Any) = this(null, qualifier, constraint)
  
  /**
   * Indicates whether the qualifier is a relational operator
   */
  def hasRelationalQualifier(): Boolean = {
    qualifier.getClass().equals(Operator)
  }
  
  override def toString(): String = {
    var text = if (obj == null) "" else obj.toString().replaceAll(" ", "")
    var text1 = if (qualifier == null) "" else qualifier.toString().replaceAll(" ", "")
    var text2 = if (constraint == null) "" else constraint.toString().replaceAll(" ", "")
    text + text1 + text2
  }
  
}

object Condition {
  
  def apply(condition: String): Condition = {
    new Condition(condition)
  }
  
}