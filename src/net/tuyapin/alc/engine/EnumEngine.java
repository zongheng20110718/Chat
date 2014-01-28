package net.tuyapin.alc.engine;

public enum EnumEngine
{
	ALC("AkalaboChat"),

	GOOGLE("GoogleJapanese"),

	YAHOO("YahooConversion"),

	SOCIAL("Social");

	private String name;

	private EnumEngine(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return this.name;
	}

	public String toString()
	{
		return this.name;
	}

	public static EnumEngine getEnum(String name)
	{
		EnumEngine[] engines = EnumEngine.values();
		for(EnumEngine engine : engines)
		{
			if(name.equals(engine.name.toString()))
			{
				return engine;
			}
		}
		return EnumEngine.ALC;
	}
}
