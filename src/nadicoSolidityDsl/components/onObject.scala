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
 * This class describes the specification of an object property as part 
 * of the aIm component of nAdico statements.
 * 
 */
case class onObject(name: String, objType: String) {
  
  def toSolidityString(): String = {
    (if (objType == null) "*Define type*" else objType) + " " + name 
  }
  
  override def toString(): String = {
    name + (if (objType == null) "" else objType)  
  }
  
}

object onObject {
  
  def apply(name: String) = new onObject(name, null)
  
}