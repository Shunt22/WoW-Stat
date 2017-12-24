package ru.shunt.wowstat.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum WowSpec {
	// @formatter:off
	WARRIOR_ARMS(WowClass.WARRIOR, 71, "Arms", "/images/specs/warrior_arms.jpg"),
	WARRIOR_FURY(WowClass.WARRIOR, 72, "Fury", "/images/specs/warrior_fury.jpg"),
	WARRIOR_PROTECTION(WowClass.WARRIOR, 73, "Protection", "/images/specs/warrior_protection.jpg"),
	PALADIN_HOLY(WowClass.PALADIN, 65, "Holy", "/images/specs/paladin_holy.jpg"),
	PALADIN_PROTECTION(WowClass.PALADIN, 66, "Protection", "/images/specs/paladin_protection.jpg"),
	PALADIN_RETRIBUTION(WowClass.PALADIN, 70, "Retribution", "/images/specs/paladin_retribution.jpg"),
	HUNTER_BEAST_MASTERY(WowClass.HUNTER, 253, "Beast Mastery", "/images/specs/hunter_beast_master.jpg"),
	HUNTER_MARKSMANSHIP(WowClass.HUNTER, 254, "Marksmanship", "/images/specs/hunter_marksmanship.jpg"),
	HUNTER_SURVIVAL(WowClass.HUNTER, 255, "Survival", "/images/specs/hunter_survival.jpg"),
	ROGUE_ASSASSINATION(WowClass.ROGUE, 259, "Assassination", "/images/specs/rogue_assassination.jpg"),
	ROGUE_OUTLAW(WowClass.ROGUE, 260, "Outlaw", "/images/specs/rogue_combat.jpg"),
	ROGUE_SUBTLETY(WowClass.ROGUE, 261, "Subtlety", "/images/specs/rogue_subtlety.jpg"),
	PRIEST_DISCIPLINE(WowClass.PRIEST, 256, "Discipline", "/images/specs/priest_discipline.jpg"),
	PRIEST_HOLY(WowClass.PRIEST, 257, "Holy", "/images/specs/priest_holy.jpg"),
	PRIEST_SHADOW(WowClass.PRIEST, 258, "Shadow", "/images/specs/priest_shadow.jpg"),
	DEATHKNIGHT_BLOOD(WowClass.DEATHKNIGHT, 250, "Blood", "/images/specs/dk_blood.jpg"),
	DEATHKINGHT_FROST(WowClass.DEATHKNIGHT, 251, "Frost", "/images/specs/dk_frost.jpg"),
	DEATHKNIGHT_UNHOLY(WowClass.DEATHKNIGHT, 252, "Unholy", "/images/specs/dk_unholy.jpg"),
	SHAMAN_ELEMENTAL(WowClass.SHAMAN, 262, "Elemental", "/images/specs/shaman_elemental.jpg"),
	SHAMAN_ENHANCEMENT(WowClass.SHAMAN, 263, "Enhancement", "/images/specs/shaman_enhancement.jpg"),
	SHAMAN_RESTORATION(WowClass.SHAMAN, 264, "Restoration", "/images/specs/shaman_restoration.jpg"),
	MAGE_ARCANE(WowClass.MAGE, 62, "Arcane", "/images/specs/mage_arcane.jpg"),
	MAGE_FIRE(WowClass.MAGE, 63, "Fire", "/images/specs/mage_fire.jpg"),
	MAGE_FROST(WowClass.MAGE, 64, "Frost", "/images/specs/mage_frost.jpg"),
	WARLOCK_AFFLICTION(WowClass.WARLOCK, 265, "Affliction", "/images/specs/warrlock_affliction.jpg"),
	WARLOCK_DEMONOLOGY(WowClass.WARLOCK, 266, "Demonology", "/images/specs/warlock_demonology.jpg"),
	WARLOCK_DESTRUCTION(WowClass.WARLOCK, 267, "Destruction", "/images/specs/warlock_destruction.jpg"),
	MONK_BREWMASTER(WowClass.MONK, 268, "Brewmaster", "/images/specs/monk_brewmaster.jpg"),
	MONK_MISTWEAVER(WowClass.MONK, 270, "Mistweaver", "/images/specs/monk_mistweaver.jpg"),
	MONK_WINDWALKER(WowClass.MONK, 269, "Windwalker", "/images/specs/monk_windwalker.jpg"),
	DRUID_BALANCE(WowClass.DRUID, 102, "Balance", "/images/specs/druid_balance.jpg"),
	DRUID_FREAL(WowClass.DRUID, 103, "Feral", "/images/specs/druid_feral.jpg"),
	DRUID_GUARDIAN(WowClass.DRUID, 104, "Guardian", "/images/specs/druid_guardian.jpg"),
	DRUID_RESTOREATION(WowClass.DRUID, 105, "Restoration", "/images/specs/druid_restoration.jpg"),
	DEMONHUNTER_HAVOC(WowClass.DEMONHUNTER, 577, "Havoc", "images/specs/dh_havoc.jpg"),
	DEMONHUNTER_VENGEANCE(WowClass.DEMONHUNTER, 581, "Vengeance", "images/specs/dh_vengeance.jpg");
	// @formatter:on
	private final int specID;
	private final WowClass clazz;
	private final String specName;
	private final String url;
	private List<List<Integer>> defaultMap;

	private static final Map<Integer, WowSpec> specIDToValueMap = new HashMap<>();

	static {
		for (WowSpec value : EnumSet.allOf(WowSpec.class)) {
			specIDToValueMap.put(value.specID, value);
		}

	}

	WowSpec(WowClass clazz, int specID, String specName, String url) {
		this.clazz = clazz;
		this.specID = specID;
		this.defaultMap = makeDefaultMap(specID);
		this.specName = specName;
		this.url = url;
	}

	public static WowSpec forID(int specID) {
		return specIDToValueMap.get(specID);
	}

	public WowClass getClazz() {
		return clazz;
	}

	public int getSpecID() {
		return specID;
	}

	public String getSpecName() {
		return specName;
	}

	@Override
	public String toString() {
		return clazz.getClassName() + " - " + specName;
	}

	public String getClassSpecName() {
		return clazz.getClassName() + " - " + specName;
	}

	public List<List<Integer>> getDefaultMap() {
		return defaultMap;
	}

	public String getUrl() {
		return url;
	}

	private List<List<Integer>> makeDefaultMap(int specID) {
		List<List<Integer>> mainList = new ArrayList<>();

		List<Integer> firstTier = new ArrayList<>();
		List<Integer> secondTier = new ArrayList<>();
		List<Integer> thirdTier = new ArrayList<>();
		List<Integer> fourthTier = new ArrayList<>();
		List<Integer> fifthTier = new ArrayList<>();
		List<Integer> sixthTier = new ArrayList<>();
		List<Integer> seventhTier = new ArrayList<>();
		switch (specID) {
			// Warrior
			default:
			case 71: // Arms
				firstTier.addAll(Arrays.asList(202297, 7384, 202161));
				secondTier.addAll(Arrays.asList(46968, 107570, 103827));
				thirdTier.addAll(Arrays.asList(215538, 772, 107574));
				fourthTier.addAll(Arrays.asList(29838, 202163, 197690));
				fifthTier.addAll(Arrays.asList(202316, 202593, 202612));
				sixthTier.addAll(Arrays.asList(227266, 248621, 207982));
				seventhTier.addAll(Arrays.asList(152278, 203179, 152277));
				break;

			case 72: // Fury
				firstTier.addAll(Arrays.asList(215556, 202296, 215568));
				secondTier.addAll(Arrays.asList(46968, 107570, 103827));
				thirdTier.addAll(Arrays.asList(215569, 206320, 107574));
				fourthTier.addAll(Arrays.asList(202224, 202163, 208154));
				fifthTier.addAll(Arrays.asList(206315, 215571, 202922));
				sixthTier.addAll(Arrays.asList(12292, 206313, 215573));
				seventhTier.addAll(Arrays.asList(46924, 202751, 118000));
				break;

			case 73: // Protection
				firstTier.addAll(Arrays.asList(46968, 107570, 103828));
				secondTier.addAll(Arrays.asList(202168, 205484, 223657));
				thirdTier.addAll(Arrays.asList(202288, 122509, 107574));
				fourthTier.addAll(Arrays.asList(223662, 202163, 203201));
				fifthTier.addAll(Arrays.asList(202560, 202561, 202095));
				sixthTier.addAll(Arrays.asList(202572, 202603, 202743));
				seventhTier.addAll(Arrays.asList(152278, 203177, 228920));
				break;
			// Paladin
			case 65: // Holy
				firstTier.addAll(Arrays.asList(223306, 114158, 196926));
				secondTier.addAll(Arrays.asList(230332, 114154, 214202));
				thirdTier.addAll(Arrays.asList(198054, 20066, 115750));
				fourthTier.addAll(Arrays.asList(183425, 183416, 183415));
				fifthTier.addAll(Arrays.asList(197646, 105809, 114165));
				sixthTier.addAll(Arrays.asList(196923, 53376, 183778));
				seventhTier.addAll(Arrays.asList(156910, 197446, 200025));
				break;

			case 66: // Protection
				firstTier.addAll(Arrays.asList(152261, 204019, 203785));
				secondTier.addAll(Arrays.asList(203776, 204035, 204023));
				thirdTier.addAll(Arrays.asList(198054, 20066, 115750));
				fourthTier.addAll(Arrays.asList(204018, 230332, 203797));
				fifthTier.addAll(Arrays.asList(213652, 204139, 204077));
				sixthTier.addAll(Arrays.asList(204150, 183778, 204054));
				seventhTier.addAll(Arrays.asList(204074, 152262, 203791));
				break;
			case 70: // Retribution
				firstTier.addAll(Arrays.asList(198038, 213757, 205228));
				secondTier.addAll(Arrays.asList(203316, 217020, 218178));
				thirdTier.addAll(Arrays.asList(234299, 20066, 115750));
				fourthTier.addAll(Arrays.asList(202271, 231832, 198034));
				fifthTier.addAll(Arrays.asList(215661, 205191, 210191));
				sixthTier.addAll(Arrays.asList(213313, 230332, 183778));
				seventhTier.addAll(Arrays.asList(223817, 231895, 210220));
				break;
			// Hunter
			case 253: // Beast Mastery
				firstTier.addAll(Arrays.asList(204308, 194397, 193532));
				secondTier.addAll(Arrays.asList(199530, 217200, 53209));
				thirdTier.addAll(Arrays.asList(109215, 199523, 199921));
				fourthTier.addAll(Arrays.asList(199528, 194306, 130392));
				fifthTier.addAll(Arrays.asList(109248, 19386, 19577));
				sixthTier.addAll(Arrays.asList(131894, 120360, 194386));
				seventhTier.addAll(Arrays.asList(201430, 199532, 191384));
				break;
			case 254: // Marksmanship
				firstTier.addAll(Arrays.asList(155228, 193533, 53238));
				secondTier.addAll(Arrays.asList(194595, 194599, 199527));
				thirdTier.addAll(Arrays.asList(109215, 199523, 199921));
				fourthTier.addAll(Arrays.asList(212431, 206817, 234588));
				fifthTier.addAll(Arrays.asList(109248, 19386, 199483));
				sixthTier.addAll(Arrays.asList(131894, 120360, 194386));
				seventhTier.addAll(Arrays.asList(214579, 198670, 199522));
				break;
			case 255: // Survival
				firstTier.addAll(Arrays.asList(204315, 200163, 201082));
				secondTier.addAll(Arrays.asList(206505, 201075, 201078));
				thirdTier.addAll(Arrays.asList(109215, 781, 199921));
				fourthTier.addAll(Arrays.asList(194277, 236698, 162488));
				fifthTier.addAll(Arrays.asList(191241, 200108, 199483));
				sixthTier.addAll(Arrays.asList(212436, 194855, 87935));
				seventhTier.addAll(Arrays.asList(194407, 199543, 191384));
				break;
			// Rogue
			case 259: // Assassination
				firstTier.addAll(Arrays.asList(196864, 193640, 16511));
				secondTier.addAll(Arrays.asList(14062, 108208, 108209));
				thirdTier.addAll(Arrays.asList(193531, 114015, 14983));
				fourthTier.addAll(Arrays.asList(108211, 79008, 31230));
				fifthTier.addAll(Arrays.asList(196861, 131511, 154904));
				sixthTier.addAll(Arrays.asList(245388, 193539, 200806));
				seventhTier.addAll(Arrays.asList(152152, 137619, 152150));
				break;
			case 260: // Outlaw
				firstTier.addAll(Arrays.asList(196937, 200733, 196938));
				secondTier.addAll(Arrays.asList(195457, 196924, 196922));
				thirdTier.addAll(Arrays.asList(193531, 114015, 14983));
				fourthTier.addAll(Arrays.asList(193546, 79008, 31230));
				fifthTier.addAll(Arrays.asList(199743, 131511, 108216));
				sixthTier.addAll(Arrays.asList(185767, 193539, 51690));
				seventhTier.addAll(Arrays.asList(5171, 137619, 152150));
				break;
			case 261: // Subtlety
				firstTier.addAll(Arrays.asList(31223, 193537, 200758));
				secondTier.addAll(Arrays.asList(14062, 108208, 108209));
				thirdTier.addAll(Arrays.asList(193531, 114015, 14983));
				fourthTier.addAll(Arrays.asList(200759, 79008, 31230));
				fifthTier.addAll(Arrays.asList(196951, 131511, 200778));
				sixthTier.addAll(Arrays.asList(245687, 193539, 238104));
				seventhTier.addAll(Arrays.asList(196976, 137619, 152150));
				break;
			// Priest
			case 256: // Discipline
				firstTier.addAll(Arrays.asList(109142, 193134, 214621));
				secondTier.addAll(Arrays.asList(121536, 64129, 193063));
				thirdTier.addAll(Arrays.asList(204263, 196704, 205367));
				fourthTier.addAll(Arrays.asList(129250, 197045, 123040));
				fifthTier.addAll(Arrays.asList(246393, 152118, 204065));
				sixthTier.addAll(Arrays.asList(204197, 110744, 120517));
				seventhTier.addAll(Arrays.asList(10060, 200309, 246287));
				break;
			case 257: // Holy
				firstTier.addAll(Arrays.asList(200128, 200153, 193155));
				secondTier.addAll(Arrays.asList(121536, 214121, 19236));
				thirdTier.addAll(Arrays.asList(204263, 200199, 196707));
				fourthTier.addAll(Arrays.asList(196985, 200209, 64901));
				fifthTier.addAll(Arrays.asList(109186, 32546, 197034));
				sixthTier.addAll(Arrays.asList(197031, 110744, 120517));
				seventhTier.addAll(Arrays.asList(200183, 193157, 204883));
				break;
			case 258: // Shadow
				firstTier.addAll(Arrays.asList(109142, 193195, 205351));
				secondTier.addAll(Arrays.asList(193173, 64129, 193063));
				thirdTier.addAll(Arrays.asList(205369, 196704, 205367));
				fourthTier.addAll(Arrays.asList(199849, 199853, 205371));
				fifthTier.addAll(Arrays.asList(199855, 155271, 162452));
				sixthTier.addAll(Arrays.asList(10060, 205385, 200174));
				seventhTier.addAll(Arrays.asList(193225, 73510, 193223));
				break;
			// Death Knight
			case 250: // Blood
				firstTier.addAll(Arrays.asList(195679, 221536, 206931));
				secondTier.addAll(Arrays.asList(194662, 212744, 211078));
				thirdTier.addAll(Arrays.asList(219786, 221699, 205727));
				fourthTier.addAll(Arrays.asList(206940, 205723, 219809));
				fifthTier.addAll(Arrays.asList(206970, 206960, 219779));
				sixthTier.addAll(Arrays.asList(206967, 194679, 206974));
				seventhTier.addAll(Arrays.asList(194844, 206977, 114556));
				break;
			case 251: // Frost
				firstTier.addAll(Arrays.asList(207057, 194878, 207061));
				secondTier.addAll(Arrays.asList(207060, 207061, 57330));
				thirdTier.addAll(Arrays.asList(207126, 194913, 207142));
				fourthTier.addAll(Arrays.asList(207161, 207167, 207170));
				fifthTier.addAll(Arrays.asList(207188, 207200, 253593));
				sixthTier.addAll(Arrays.asList(207230, 194909, 194912));
				seventhTier.addAll(Arrays.asList(207256, 152279, 207127));
				break;
			case 252: // Unholy
				firstTier.addAll(Arrays.asList(194916, 207264, 207269));
				secondTier.addAll(Arrays.asList(207317, 194917, 194918));
				thirdTier.addAll(Arrays.asList(207289, 207305, 207311));
				fourthTier.addAll(Arrays.asList(207313, 108194, 207316));
				fifthTier.addAll(Arrays.asList(207321, 207319, 212763));
				sixthTier.addAll(Arrays.asList(198943, 207346, 207272));
				seventhTier.addAll(Arrays.asList(207349, 152280, 130736));
				break;
			// Shaman
			case 262:// Elemental
				firstTier.addAll(Arrays.asList(201909, 170374, 210643));
				secondTier.addAll(Arrays.asList(192063, 108281, 192077));
				thirdTier.addAll(Arrays.asList(192058, 51485, 196932));
				fourthTier.addAll(Arrays.asList(210707, 192087, 16166));
				fifthTier.addAll(Arrays.asList(192235, 117013, 117014));
				sixthTier.addAll(Arrays.asList(192222, 192249, 108283));
				seventhTier.addAll(Arrays.asList(114050, 210689, 210714));
				break;
			case 263:// Enhancement
				firstTier.addAll(Arrays.asList(201898, 201900, 19792));
				secondTier.addAll(Arrays.asList(215864, 196884, 192077));
				thirdTier.addAll(Arrays.asList(192058, 51485, 196932));
				fourthTier.addAll(Arrays.asList(192106, 192087, 210853));
				fifthTier.addAll(Arrays.asList(192234, 210727, 210731));
				sixthTier.addAll(Arrays.asList(192246, 197211, 197214));
				seventhTier.addAll(Arrays.asList(114051, 246035, 188089));
				break;
			case 264:// Restoration
				firstTier.addAll(Arrays.asList(200071, 73685, 200072));
				secondTier.addAll(Arrays.asList(192063, 192088, 192077));
				thirdTier.addAll(Arrays.asList(192058, 51485, 196932));
				fourthTier.addAll(Arrays.asList(197464, 108281, 200076));
				fifthTier.addAll(Arrays.asList(207399, 198838, 207401));
				sixthTier.addAll(Arrays.asList(197467, 157153, 108283));
				seventhTier.addAll(Arrays.asList(114052, 197995, 157154));
				break;
			// Mage
			case 62: // Arcane
				firstTier.addAll(Arrays.asList(205022, 236628, 205035));
				secondTier.addAll(Arrays.asList(212653, 86949, 235463));
				thirdTier.addAll(Arrays.asList(55342, 116011, 1463));
				fourthTier.addAll(Arrays.asList(157980, 205032, 205028));
				fifthTier.addAll(Arrays.asList(235711, 113724, 205036));
				sixthTier.addAll(Arrays.asList(114923, 157976, 205039));
				seventhTier.addAll(Arrays.asList(155147, 234302, 153626));
				break;
			case 63: // Fire
				firstTier.addAll(Arrays.asList(205020, 205023, 205026));
				secondTier.addAll(Arrays.asList(212653, 157981, 235365));
				thirdTier.addAll(Arrays.asList(55342, 116011, 1463));
				fourthTier.addAll(Arrays.asList(235870, 205029, 205033));
				fifthTier.addAll(Arrays.asList(236058, 113724, 205036));
				sixthTier.addAll(Arrays.asList(44457, 157976, 205037));
				seventhTier.addAll(Arrays.asList(155148, 198929, 153561));
				break;
			case 64: // Frost
				firstTier.addAll(Arrays.asList(205021, 205024, 205027));
				secondTier.addAll(Arrays.asList(212653, 108839, 235297));
				thirdTier.addAll(Arrays.asList(55342, 116011, 1463));
				fourthTier.addAll(Arrays.asList(157997, 205030, 56377));
				fifthTier.addAll(Arrays.asList(235224, 113724, 205036));
				sixthTier.addAll(Arrays.asList(112948, 157976, 205038));
				seventhTier.addAll(Arrays.asList(155149, 199786, 153595));
				break;
			// Warlock
			case 265: // Affliction
				firstTier.addAll(Arrays.asList(48181, 196102, 235155));
				secondTier.addAll(Arrays.asList(196105, 196103, 235157));
				thirdTier.addAll(Arrays.asList(48018, 6789, 5484));
				fourthTier.addAll(Arrays.asList(205179, 196226, 196098));
				fifthTier.addAll(Arrays.asList(219272, 111400, 108416));
				sixthTier.addAll(Arrays.asList(152107, 108501, 108503));
				seventhTier.addAll(Arrays.asList(234876, 63106, 215941));
				break;
			case 266: // Demonology
				firstTier.addAll(Arrays.asList(196269, 205181, 205145));
				secondTier.addAll(Arrays.asList(196270, 196272, 196277));
				thirdTier.addAll(Arrays.asList(219272, 6789, 30283));
				fourthTier.addAll(Arrays.asList(196283, 196605, 196098));
				fifthTier.addAll(Arrays.asList(48018, 111400, 108416));
				sixthTier.addAll(Arrays.asList(152107, 108501, 171975));
				seventhTier.addAll(Arrays.asList(205180, 157695, 215941));
				break;
			case 267: // Destruction
				firstTier.addAll(Arrays.asList(196406, 205184, 17877));
				secondTier.addAll(Arrays.asList(205148, 196412, 235157));
				thirdTier.addAll(Arrays.asList(48018, 6789, 30283));
				fourthTier.addAll(Arrays.asList(152108, 196408, 196098));
				fifthTier.addAll(Arrays.asList(219272, 111400, 108416));
				sixthTier.addAll(Arrays.asList(152107, 108501, 108503));
				seventhTier.addAll(Arrays.asList(196410, 196447, 215941));
				break;
			// Monk
			case 268: // Brewmaster
				firstTier.addAll(Arrays.asList(123986, 196607, 115098));
				secondTier.addAll(Arrays.asList(115008, 116841, 115173));
				thirdTier.addAll(Arrays.asList(196721, 115399, 196719));
				fourthTier.addAll(Arrays.asList(116844, 115315, 119381));
				fifthTier.addAll(Arrays.asList(122281, 237076, 122278));
				sixthTier.addAll(Arrays.asList(116847, 132578, 196730));
				seventhTier.addAll(Arrays.asList(196738, 196736, 196737));
				break;
			case 270: // Mistweaver
				firstTier.addAll(Arrays.asList(123986, 124081, 115098));
				secondTier.addAll(Arrays.asList(115008, 116841, 115173));
				thirdTier.addAll(Arrays.asList(197915, 210802, 197900));
				fourthTier.addAll(Arrays.asList(116844, 198898, 119381));
				fifthTier.addAll(Arrays.asList(122281, 122783, 122278));
				sixthTier.addAll(Arrays.asList(116847, 198664, 115313));
				seventhTier.addAll(Arrays.asList(197908, 197895, 210804));
				break;
			case 269: // Windwalker
				firstTier.addAll(Arrays.asList(123986, 196607, 115098));
				secondTier.addAll(Arrays.asList(115008, 116841, 115173));
				thirdTier.addAll(Arrays.asList(115288, 115396, 121817));
				fourthTier.addAll(Arrays.asList(116844, 115315, 119381));
				fifthTier.addAll(Arrays.asList(122281, 122783, 122278));
				sixthTier.addAll(Arrays.asList(116847, 123904, 196740));
				seventhTier.addAll(Arrays.asList(196743, 152175, 152173));
				break;
			// Druid
			case 102: // Balance
				firstTier.addAll(Arrays.asList(205636, 202425, 202345));
				secondTier.addAll(Arrays.asList(108238, 102280, 132302));
				thirdTier.addAll(Arrays.asList(202175, 197491, 197492));
				fourthTier.addAll(Arrays.asList(5211, 102359, 132469));
				fifthTier.addAll(Arrays.asList(114107, 102560, 202347));
				sixthTier.addAll(Arrays.asList(202342, 202359, 202360));
				seventhTier.addAll(Arrays.asList(202770, 202354, 202430));
				break;
			case 103: // Feral
				firstTier.addAll(Arrays.asList(202021, 202022, 155580));
				secondTier.addAll(Arrays.asList(108238, 102280, 132302));
				thirdTier.addAll(Arrays.asList(197488, 217615, 197492));
				fourthTier.addAll(Arrays.asList(5211, 102359, 132469));
				fifthTier.addAll(Arrays.asList(158476, 102543, 202032));
				sixthTier.addAll(Arrays.asList(202031, 202028, 52610));
				seventhTier.addAll(Arrays.asList(236068, 155672, 202060));
				break;
			case 104: // Guardian
				firstTier.addAll(Arrays.asList(203953, 155835, 203962));
				secondTier.addAll(Arrays.asList(204021, 236748, 132302));
				thirdTier.addAll(Arrays.asList(197488, 202155, 197492));
				fourthTier.addAll(Arrays.asList(5211, 102359, 132469));
				fifthTier.addAll(Arrays.asList(158477, 102558, 203964));
				sixthTier.addAll(Arrays.asList(203974, 155578, 203965));
				seventhTier.addAll(Arrays.asList(204053, 204066, 80313));
				break;
			case 105: // Restoration
				firstTier.addAll(Arrays.asList(200383, 102351, 207383));
				secondTier.addAll(Arrays.asList(108238, 102280, 132302));
				thirdTier.addAll(Arrays.asList(197632, 197490, 197491));
				fourthTier.addAll(Arrays.asList(5211, 102359, 132469));
				fifthTier.addAll(Arrays.asList(158478, 33891, 200390));
				sixthTier.addAll(Arrays.asList(207385, 197073, 155675));
				seventhTier.addAll(Arrays.asList(155577, 197061, 197721));
				break;
			// Demon Hunter
			case 577: // Havoc
				firstTier.addAll(Arrays.asList(192939, 232893, 203550));
				secondTier.addAll(Arrays.asList(203551, 203555, 206478));
				thirdTier.addAll(Arrays.asList(206475, 206416, 206473));
				fourthTier.addAll(Arrays.asList(196555, 205411, 204909));
				fifthTier.addAll(Arrays.asList(206476, 211881, 206491));
				sixthTier.addAll(Arrays.asList(203556, 206477, 193897));
				seventhTier.addAll(Arrays.asList(211048, 211053, 213410));
				break;
			case 581: // Vengeance
				firstTier.addAll(Arrays.asList(207550, 207548, 209400));
				secondTier.addAll(Arrays.asList(207697, 227174, 207739));
				thirdTier.addAll(Arrays.asList(213241, 227322, 211881));
				fourthTier.addAll(Arrays.asList(218612, 209795, 217996));
				fifthTier.addAll(Arrays.asList(207666, 202138, 209281));
				sixthTier.addAll(Arrays.asList(212084, 203753, 218679));
				seventhTier.addAll(Arrays.asList(209258, 207810, 227225));
				break;
		}

		mainList.addAll(Arrays.asList(firstTier, secondTier, thirdTier, fourthTier, fifthTier, sixthTier, seventhTier));
		return mainList;
	}

}
