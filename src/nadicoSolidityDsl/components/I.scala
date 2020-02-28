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
 * This class describes the aIm component of nAdico statements.
 * 
 */
case class I(action : String, obj : onObject, target: onTarget) {

  def this(action: String) = this(action, null, null)
  
  def this(action: String, obj: String) = this(action, onObject(obj), null)
  
}

object I {
  
  def apply(action: String): I = new I(action)
  
  def apply(action: String, obj: String): I = new I(action, obj)
  
  def apply(action: String, obj: onObject): I = new I(action, obj, null)
  
  def apply(action: String, obj: String, target: String): I = new I(action, onObject(obj), onTarget(target))
  
}