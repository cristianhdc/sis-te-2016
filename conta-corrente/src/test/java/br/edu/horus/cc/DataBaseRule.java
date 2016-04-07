package br.edu.horus.cc;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class DataBaseRule extends Conexao implements TestRule {

	@Override
	public Statement apply(Statement base, Description description){
		criarBanco();		
		return base;
	}

}
