package org.irmc.industrialrevival.api.elements.compounds;

import org.irmc.industrialrevival.api.elements.reaction.ReactCondition;
import org.irmc.industrialrevival.utils.Debug;
import org.irmc.industrialrevival.utils.KeyUtil;

@SuppressWarnings("unused")
public class ChemicalFormulas {
    static {
        ChemicalCompounds.init();
    }

    public static final ChemicalFormula Zn_H2SO4_to_ZnSO4_H2 = new ChemicalFormula(
            KeyUtil.customKey("Zn_H2SO4_to_ZnSO4_H2"),
            "Zn+H2SO4===ZnSO4+H2"
    );
    public static final ChemicalFormula NaOH_H2SO4_to_Na2SO4_H2O = new ChemicalFormula(
            KeyUtil.customKey("NaOH_H2SO4_to_Na2SO4_H2O"),
            "2NaOH+H2SO4===Na2SO4+2H2O"
    );
    public static final ChemicalFormula CaCl2_H2SO4_to_CaSO4_HCl = new ChemicalFormula(
            KeyUtil.customKey("CaCl_H2SO4_to_CaSO4_HCl"),
            "CaCl2+H2SO4===CaSO4+2HCl",
            new ReactCondition[] {ReactCondition.HIGH_TEMPERATURE}
    );
    public static final ChemicalFormula KMnO4_to_K2MnO4_MnO2_O2 = new ChemicalFormula(
            KeyUtil.customKey("KMnO4_to_K2MnO4_MnO2_O2"),
            "2KMnO4===K2MnO4+MnO2+O2",
            new ReactCondition[] {ReactCondition.HEATING}
    );
    public static final ChemicalFormula CaCO3_2HCl_to_CaCl2_H2O_CO2 = new ChemicalFormula(
            KeyUtil.customKey("CaCO3_2HCl_to_CaCl2_H2O_CO2"),
            "CaCO3+2HCl===CaCl2+H2O+CO2"
    );
    public static final ChemicalFormula KCLO3_to_KCl_O2 = new ChemicalFormula(
            KeyUtil.customKey("KCLO3_to_KCl_O2"),
            "2KClO3===2KCl+3O2",
            new ReactCondition[] {ReactCondition.HEATING, ReactCondition.asCatalyzer(ChemicalCompounds.MnO2)}
    );
    public static final ChemicalFormula CaCO3_to_CaO_CO2 = new ChemicalFormula(
            KeyUtil.customKey("CaCO3_to_CaO_CO2"),
            "CaCO3===CaO+CO2",
            new ReactCondition[] {ReactCondition.HIGH_TEMPERATURE}
    );
    public static final ChemicalFormula CaCl2_2AgNO3_to_CaNO3_2_2AgCl = new ChemicalFormula(
            KeyUtil.customKey("CaCl2_2AgNO3_to_Ca-NO3-_2_2AgCl"),
            "CaCl2+2AgNO3===Ca(NO3)_2+2AgCl"
    );
    public static final ChemicalFormula H2O2_to_2H2O_O2 = new ChemicalFormula(
            KeyUtil.customKey("H2O2_to_2H2O_O2"),
            "2H2O2===2H2O+O2",
            new ReactCondition[] {ReactCondition.asCatalyzer(ChemicalCompounds.MnO2)}
    );
    public static final ChemicalFormula H2O_to_H2_O2 = new ChemicalFormula(
            KeyUtil.customKey("H2O_to_H2_O2"),
            "2H2O===2H2+O2",
            new ReactCondition[] {ReactCondition.ELECTROLYSIS}
    );

    public static void register() {
        Zn_H2SO4_to_ZnSO4_H2.register();
        NaOH_H2SO4_to_Na2SO4_H2O.register();
        CaCl2_H2SO4_to_CaSO4_HCl.register();
        KMnO4_to_K2MnO4_MnO2_O2.register();
        CaCO3_2HCl_to_CaCl2_H2O_CO2.register();
        KCLO3_to_KCl_O2.register();
        CaCO3_to_CaO_CO2.register();
        CaCl2_2AgNO3_to_CaNO3_2_2AgCl.register();
        H2O2_to_2H2O_O2.register();
        H2O_to_H2_O2.registerElectrolysis();
    }
}
