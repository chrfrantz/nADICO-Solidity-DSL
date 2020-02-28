package nadicoSolidityDsl

import nadicoSolidityDsl.components._
import nadicoSolidityDsl.enums.Combinator
import nadicoSolidityDsl.enums.Deontic

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
 * This class describes the core structure of a nADICO statement.
 * 
 */
case class NAdicoStatement(attributes : A, deontic : D, aim : I, conditions : C, var orElse : NAdicoExpression) extends NAdicoExpression {
  
  def this(aim: I) = this(null, null, aim, null, null)
  
  def this(attributes: A, aim: I) = this(attributes, null, aim, null, null)
  
  def this(attributes: A, aim: I, conditions: C) = this(attributes, null, aim, conditions, null)
  
  def this(attributes: A, deontic: D, aim: I) = this(attributes, deontic, aim, null, null)
  
  def this(attributes: A, deontic: D, aim: I, conditions: C) = this(attributes, deontic, aim, conditions, null)
  
  def AND(nadico: NAdicoExpression): NAdicoCombination = {
    NAdicoCombination(this, Combinator.AND, nadico)
  }
  
  def OR(nadico: NAdicoExpression): NAdicoCombination = {
    NAdicoCombination(this, Combinator.OR, nadico)
  }
  
  def XOR(nadico: NAdicoExpression): NAdicoCombination = {
    NAdicoCombination(this, Combinator.XOR, nadico)
  }

}

object NAdico {
  
  def apply(aim: String): NAdicoStatement = {
    new NAdicoStatement(I(aim))
  }
  
  def apply(attributes: A, aim: I): NAdicoStatement = {
    new NAdicoStatement(attributes, aim)
  }
  
  def apply(attributes: String, aim: String): NAdicoStatement = {
    new NAdicoStatement(A(attributes), I(aim))
  }
  
  def apply(attributes: String, aim: I): NAdicoStatement = {
    new NAdicoStatement(A(attributes), aim)
  }
  
  def apply(attributes: A, aim: String): NAdicoStatement = {
    new NAdicoStatement(attributes, I(aim))
  }
  
  def apply(attributes: A, aim: I, conditions: C): NAdicoStatement = {
    new NAdicoStatement(attributes, aim, conditions)
  }
  
  def apply(attributes: String, aim: String, conditions: C): NAdicoStatement = {
    new NAdicoStatement(A(attributes), I(aim), conditions)
  }
  
  def apply(attributes: A, deontic: D, aim: I): NAdicoStatement = {
    new NAdicoStatement(attributes, deontic, aim)
  }
  
  def apply(attributes: String, deontic: D, aim: String): NAdicoStatement = {
    new NAdicoStatement(A(attributes), deontic, I(aim))
  }
  
  def apply(attributes: String, deontic: Deontic.Value, aim: String): NAdicoStatement = {
    new NAdicoStatement(A(attributes), D(deontic), I(aim))
  }
  
  def apply(attributes: String, deontic: D, aim: I): NAdicoStatement = {
    new NAdicoStatement(A(attributes), deontic, aim)
  }
  
  def apply(attributes: String, deontic: Deontic.Value, aim: I): NAdicoStatement = {
    new NAdicoStatement(A(attributes), D(deontic), aim)
  }
  
  def apply(attributes: A, deontic: D, aim: String): NAdicoStatement = {
    new NAdicoStatement(attributes, deontic, I(aim))
  }
  
  def apply(attributes: A, deontic: Deontic.Value, aim: String): NAdicoStatement = {
    new NAdicoStatement(attributes, D(deontic), I(aim))
  }
  
  def apply(attributes: A, deontic: D, aim: I, conditions: C): NAdicoStatement = {
    new NAdicoStatement(attributes, deontic, aim, conditions)
  }
  
  def apply(attributes: A, deontic: Deontic.Value, aim: I, conditions: C): NAdicoStatement = {
    new NAdicoStatement(attributes, D(deontic), aim, conditions)
  }
  
  def apply(attributes: A, deontic: Deontic.Value, aim: I, conditions: String): NAdicoStatement = {
    new NAdicoStatement(attributes, D(deontic), aim, C(conditions))
  }
  
  def apply(attributes: String, deontic: D, aim: I, conditions: C): NAdicoStatement = {
    new NAdicoStatement(A(attributes), deontic, aim, conditions)
  }
  
  def apply(attributes: String, deontic: D, aim: I, conditions: String): NAdicoStatement = {
    new NAdicoStatement(A(attributes), deontic, aim, C(conditions))
  }
  
  def apply(attributes: String, deontic: Deontic.Value, aim: I, conditions: C): NAdicoStatement = {
    new NAdicoStatement(A(attributes), D(deontic), aim, conditions)
  }
  
  def apply(attributes: String, deontic: Deontic.Value, aim: I, conditions: String): NAdicoStatement = {
    new NAdicoStatement(A(attributes), D(deontic), aim, C(conditions))
  }
  
  def apply(attributes: A, deontic: D, aim: String, conditions: C): NAdicoStatement = {
    new NAdicoStatement(attributes, deontic, I(aim), conditions)
  }
  
  def apply(attributes: A, deontic: D, aim: String, conditions: String): NAdicoStatement = {
    new NAdicoStatement(attributes, deontic, I(aim), C(conditions))
  }
  
  def apply(attributes: A, deontic: Deontic.Value, aim: String, conditions: C): NAdicoStatement = {
    new NAdicoStatement(attributes, D(deontic), I(aim), conditions)
  }
  
  def apply(attributes: A, deontic: Deontic.Value, aim: String, conditions: String): NAdicoStatement = {
    new NAdicoStatement(attributes, D(deontic), I(aim), C(conditions))
  }
  
  def apply(attributes: String, deontic: D, aim: String, conditions: C): NAdicoStatement = {
    new NAdicoStatement(A(attributes), deontic, I(aim), conditions)
  }
  
  def apply(attributes: String, deontic: D, aim: String, conditions: String): NAdicoStatement = {
    new NAdicoStatement(A(attributes), deontic, I(aim), C(conditions))
  }
  
  def apply(attributes: String, deontic: Deontic.Value, aim: String, conditions: C): NAdicoStatement = {
    new NAdicoStatement(A(attributes), D(deontic), I(aim), conditions)
  }
  
  def apply(attributes: String, deontic: Deontic.Value, aim: String, conditions: String): NAdicoStatement = {
    new NAdicoStatement(A(attributes), D(deontic), I(aim), C(conditions))
  }
  
}