package net.twisterrob.astro.bazi.model

import net.twisterrob.astro.bazi.model.HeavenlyStem.Bing
import net.twisterrob.astro.bazi.model.HeavenlyStem.Ding
import net.twisterrob.astro.bazi.model.HeavenlyStem.Geng
import net.twisterrob.astro.bazi.model.HeavenlyStem.Gui
import net.twisterrob.astro.bazi.model.HeavenlyStem.Ji
import net.twisterrob.astro.bazi.model.HeavenlyStem.Jia
import net.twisterrob.astro.bazi.model.HeavenlyStem.Ren
import net.twisterrob.astro.bazi.model.HeavenlyStem.Wu
import net.twisterrob.astro.bazi.model.HeavenlyStem.Xin
import net.twisterrob.astro.bazi.model.HeavenlyStem.Yi

/**
 * Chinese Zodiac / shēngxiào (生肖 / 生肖).
 *
 * https://en.wikipedia.org/wiki/Chinese_zodiac
 *
 * Nice summary of hidden stems:
 * https://imperialharvest.com/wp-content/uploads/2023/10/Blog_10.20_Hidden-Heavenly-Stems-in-Earthly-Branches_Hidden-Heavenly-Stems.png
 */
public enum class Zodiac(

	/**
	 * Charged element.
	 */
	public val charge: HeavenlyStem,

	/**
	 * Principal Qi of the hidden stem (主气) of the Zodiac's associated Earthly Branch.
	 *
	 * See https://imperialharvest.com/blog/hidden-heavenly-stems/.
	 */
	public val principalQi: HeavenlyStem,

	/**
	 * Central Qi of the hidden stem (中气) of the Zodiac's associated Earthly Branch.
	 *
	 * See https://imperialharvest.com/blog/hidden-heavenly-stems/
	 */
	public val centralQi: HeavenlyStem?,

	/**
	 * Residual Qi of the hidden stem (余气) of the Zodiac's associated Earthly Branch.
	 *
	 * See https://imperialharvest.com/blog/hidden-heavenly-stems/
	 */
	public val residualQi: HeavenlyStem?,
) {

	/**
	 * 鼠: Rat.
	 */
	Rat(
		charge = Ren,
		principalQi = Gui,
		centralQi = null,
		residualQi = null,
	),

	/**
	 * 牛: Ox / Cow.
	 */
	Ox(
		charge = Ji,
		principalQi = Ji,
		centralQi = Gui,
		residualQi = Xin,
	),

	/**
	 * 虎: Tiger.
	 */
	Tiger(
		charge = Jia,
		principalQi = Jia,
		centralQi = Bing,
		residualQi = Wu,
	),

	/**
	 * 兔: Rabbit.
	 */
	Rabbit(
		charge = Yi,
		principalQi = Yi,
		centralQi = null,
		residualQi = null,
	),

	/**
	 * 龙: Dragon.
	 */
	Dragon(
		charge = Wu,
		principalQi = Wu,
		centralQi = Yi,
		residualQi = Gui,
	),

	/**
	 * 蛇: Snake.
	 */
	Snake(
		charge = Ding,
		principalQi = Bing,
		centralQi = Wu,
		residualQi = Geng,
	),

	/**
	 * 马: Horse.
	 */
	Horse(
		charge = Bing,
		principalQi = Ding,
		centralQi = Ji,
		residualQi = null,
	),

	/**
	 * 羊: Goat / Sheep.
	 */
	Goat(
		charge = Ji,
		principalQi = Ji,
		centralQi = Ding,
		residualQi = Yi,
	),

	/**
	 * 猴: Monkey.
	 */
	Monkey(
		charge = Geng,
		principalQi = Geng,
		centralQi = Ren,
		residualQi = Wu,
	),

	/**
	 * 鸡: Rooster.
	 */
	Rooster(
		charge = Xin,
		principalQi = Xin,
		centralQi = null,
		residualQi = null,
	),

	/**
	 * 狗: Dog.
	 */
	Dog(
		charge = Wu,
		principalQi = Wu,
		centralQi = Xin,
		residualQi = Ding,
	),

	/**
	 * 猪: Pig / Boar.
	 */
	Pig(
		charge = Gui,
		principalQi = Ren,
		centralQi = Jia,
		residualQi = null,
	),

	;

	public companion object;
}
