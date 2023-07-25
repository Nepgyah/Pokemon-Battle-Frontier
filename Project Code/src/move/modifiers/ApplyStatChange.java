package move.modifiers;

/* NOTE: This only refers to statistic stat change chance (Ex: lower defense
 * Status effects (Ex: Paralyze ) are retreived through their specific interface)
 */
public interface ApplyStatChange {
	double getApplyChance();
}
