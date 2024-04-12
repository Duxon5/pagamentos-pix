package com.banco.ficticio.pix;

import java.util.UUID;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.banco.ficticio.pix.enums.TipoChaveEnum;
import com.banco.ficticio.pix.util.Util;

public class UtilTest {

	@Test
	public void nullToStringTest() {
		Assert.assertEquals("", Util.nullToString(null));
		Assert.assertEquals("a", Util.nullToString(new String("a")));
		Assert.assertEquals("124", Util.nullToString(new Long(124)));
		Assert.assertEquals("18", Util.nullToString(new Integer(18)));
	}

	@Test
	public void cpfChaveToTipoChaveTest() {
		Assert.assertEquals(TipoChaveEnum.CPF, Util.chaveToTipoChave("72463081643"));
		Assert.assertEquals(TipoChaveEnum.CPF, Util.chaveToTipoChave("41023752603"));
		Assert.assertEquals(TipoChaveEnum.CPF, Util.chaveToTipoChave("21902764544"));
	}

	@Test
	public void telefoneChaveToTipoChaveTest() {
		Assert.assertEquals(TipoChaveEnum.TELEFONE, Util.chaveToTipoChave("1121165895"));
		Assert.assertEquals(TipoChaveEnum.TELEFONE, Util.chaveToTipoChave("1140028922"));
		Assert.assertEquals(TipoChaveEnum.TELEFONE, Util.chaveToTipoChave("1922224444"));
	}

	@Test
	public void emailChaveToTipoChaveTest() {
		Assert.assertEquals(TipoChaveEnum.EMAIL, Util.chaveToTipoChave("a@a.com"));
		Assert.assertEquals(TipoChaveEnum.EMAIL, Util.chaveToTipoChave("email@uol.com.br"));
		Assert.assertEquals(TipoChaveEnum.EMAIL, Util.chaveToTipoChave("user@yahoo.com.br"));
	}

	@Test
	public void aleatoriaChaveToTipoChaveTest() {
		Assert.assertEquals(TipoChaveEnum.ALEATORIA, Util.chaveToTipoChave(UUID.randomUUID().toString()));
		Assert.assertEquals(TipoChaveEnum.ALEATORIA, Util.chaveToTipoChave(UUID.randomUUID().toString()));
		Assert.assertEquals(TipoChaveEnum.ALEATORIA, Util.chaveToTipoChave(UUID.randomUUID().toString()));
	}

	@Test
	public void invalidaChaveToTipoChaveTest() {
		Assert.assertEquals(TipoChaveEnum.INVALIDA, Util.chaveToTipoChave(null));
		Assert.assertEquals(TipoChaveEnum.INVALIDA, Util.chaveToTipoChave("a@@a.com"));
		Assert.assertEquals(TipoChaveEnum.INVALIDA, Util.chaveToTipoChave("123456"));
	}
	
}
