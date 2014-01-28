package net.tuyapin.alc.engine;

public class SocialIMEInput extends InputEngine {

	public SocialIMEInput()
	{
		super("http://www.social-ime.com/api/?string=");
	}

	@Override
	public String getText(String text)
	{
		text = this.getResponse(text);

		StringBuilder builder = new StringBuilder();

		String[] array = text.split("\n");
		for(String single : array)
		{
			builder.append(single.split("\t")[0]);
		}

		return builder.toString();
	}

	@Override
	public String getEncode()
	{
		//死ね
		return "EUC-JP";
	}

}
