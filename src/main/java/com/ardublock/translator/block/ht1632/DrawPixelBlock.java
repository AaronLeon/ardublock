// Not now used by standard blocks. Retained as long as it is referenced by legacy blocks.
package com.ardublock.translator.block.ht1632;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.NumberBlock;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class DrawPixelBlock extends TranslatorBlock
{

	public DrawPixelBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		String ht1632Name = "matrix";

		translator.addHeaderFile("HT1632.h");
		translator.addDefinitionCommand("HT1632LEDMatrix " + ht1632Name + " = HT1632LEDMatrix(2,3,4);\n");
		translator.addSetupCommand(ht1632Name + ".begin(HT1632_COMMON_16NMOS);\n");

		TranslatorBlock x = this.getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock y = this.getRequiredTranslatorBlockAtSocket(1);
		TranslatorBlock clear = this.getRequiredTranslatorBlockAtSocket(2);

		String ret = ht1632Name + ".drawPixel(" + x.toCode() + "," + y.toCode() + "," + clear.toCode() + ");\n";

		return codePrefix + ret + codeSuffix;
	}

}
