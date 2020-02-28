package nadicoSolidityDsl.components

import nadicoSolidityDsl.enums.Combinator

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
 * This class describes combinations of individual conditions as part of the
 * Conditions component of nAdico statements.
 * 
 */
case class ConditionCombination(left: ConditionExpression, combinator: Combinator.Value, right: ConditionExpression) extends ConditionExpression {
  
}