package com.alta189.minemail.addons;

public class sqlFormatter {
	@SuppressWarnings("unused")
	private AddonManager manageAddons;
	
	public sqlFormatter(AddonManager instance) {
		this.manageAddons = instance;
	}

	public Boolean check(String value) {
		if (value.contains("@")) {
			return false;
		} else if (value.contains("#")) {
			return false;
		} else if (value.contains("%")) {
			return false;
		} else if (value.contains("^")) {
			return false;
		} else if (value.contains("&")) {
			return false;
		} else if (value.contains("*")) {
			return false;
		} else if (value.contains("(")) {
			return false;
		} else if (value.contains(")")) {
			return false;
		} else if (value.contains("-")) {
			return false;
		} else if (value.contains("+")) {
			return false;
		} else if (value.contains("=")) {
			return false;
		} else if (value.contains("[")) {
			return false;
		} else if (value.contains("]")) {
			return false;
		} else if (value.contains("\"")) {
			return false;
		} else if (value.contains("\\")) {
			return false;
		} else if (value.contains("'")) {
			return false;
		} else if (value.contains("|")) {
			return false;
		} else if (value.contains("<")) {
			return false;
		} else if (value.contains(">")) {
			return false;
		} else if (value.contains("/")) {
			return false;
		} else if (value.contains("`")) {
			return false;
		} else if (value.contains("~")) {
			return false;
		} else if (value.contains(":")) {
			return false;
		} else if (value.contains(";")) {
			return false;
		} else if (value.contains("{")) {
			return false;
		} else if (value.contains("}")) {
			return false;
		}
		
		return true;
	}
	
	public String fix(String input) {
		String result = input;
		if (result.contains("@")) {
			result.replace("@", "");
		} if (result.contains("#")) {
			result.replace("#", "");
		} if (result.contains("%")) {
			result.replace("%", "");
		} if (result.contains("^")) {
			result.replace("^", "");
		} if (result.contains("&")) {
			result.replace("&", "");
		} if (result.contains("*")) {
			result.replace("*", "");
		} if (result.contains("(")) {
			result.replace("(", "");
		} if (result.contains(")")) {
			result.replace(")", "");
		} if (result.contains("-")) {
			result.replace("-", "");
		} if (result.contains("+")) {
			result.replace("+", "");
		} if (result.contains("=")) {
			result.replace("=", "");
		} if (result.contains("[")) {
			result.replace("[", "");
		} if (result.contains("]")) {
			result.replace("]", "");
		} if (result.contains("\"")) {
			result.replace("\"", "");
		} if (result.contains("\\")) {
			result.replace("\\", "");
		} if (result.contains("'")) {
			result.replace("'", "");
		} if (result.contains("|")) {
			result.replace("|", "");
		} if (result.contains("<")) {
			result.replace("<", "");
		} if (result.contains(">")) {
			result.replace(">", "");
		} if (result.contains("/")) {
			result.replace("/", "");
		} if (result.contains("`")) {
			result.replace("`", "");
		} if (result.contains("~")) {
			result.replace("~", "");
		} if (result.contains(":")) {
			result.replace(":", "");
		} if (result.contains(";")) {
			result.replace(";", "");
		} if (result.contains("{")) {
			result.replace("{", "");
		} if (result.contains("}")) {
			result.replace("}", "");
		}	
		return result;
	}
}
