/*
 * generated by Xtext 2.12.0
 */
package org.xtext.example.ide

import com.google.inject.Guice
import org.eclipse.xtext.util.Modules2
import org.xtext.example.CompRuntimeModule
import org.xtext.example.CompStandaloneSetup

/**
 * Initialization support for running Xtext languages as language servers.
 */
class CompIdeSetup extends CompStandaloneSetup {

	override createInjector() {
		Guice.createInjector(Modules2.mixin(new CompRuntimeModule, new CompIdeModule))
	}
	
}