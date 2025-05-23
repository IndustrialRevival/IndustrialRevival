package org.irmc.industrialrevival.api.objects.exceptions;

import org.irmc.industrialrevival.api.elements.compounds.ChemicalFormula;

public class FormulaConflictException extends RuntimeException {
    public FormulaConflictException(String message) {
        super(message);
    }

    public FormulaConflictException(ChemicalFormula formula1, ChemicalFormula formula2) {
        super(String.format("""
                Formula conflict detected:
                Formula id: %s
                Formula1: %s
                Formula2: %s
                
                The conflicting formula will not be registered.
                """, formula1.getId(), formula1.getRawFormula(), formula2.getRawFormula()));
    }
}
