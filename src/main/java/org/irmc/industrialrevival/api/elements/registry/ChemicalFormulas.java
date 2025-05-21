package org.irmc.industrialrevival.api.elements.registry;

import org.irmc.industrialrevival.api.elements.compounds.ChemicalFormula;
import org.irmc.industrialrevival.api.elements.reaction.ReactCondition;
import org.irmc.industrialrevival.utils.KeyUtil;

import java.lang.reflect.Field;
import java.util.Set;

@SuppressWarnings("unused")
public class ChemicalFormulas {
    public static void register() {
        int id = 148000;
        new ChemicalFormula(
                ++id,
                "4P+5O2===2P2O5",
                ReactCondition.LIGHT
        );
        new ChemicalFormula(
                ++id,
                "4P+5O2===2P2O5"
        );
        new ChemicalFormula(
                ++id,
                "C+O2===CO2",
                ReactCondition.LIGHT
        );
        new ChemicalFormula(
                ++id,
                "2C+O2===2CO",
                ReactCondition.LIGHT
        );
        new ChemicalFormula(
                ++id,
                "S+O2===SO2",
                ReactCondition.LIGHT
        );
        new ChemicalFormula(
                ++id,
                "3Fe+2O2===Fe3O4",
                ReactCondition.LIGHT
        );
        new ChemicalFormula(
                ++id,
                "4Al+3O2===2Al2O3",
                ReactCondition.LIGHT
        );
        new ChemicalFormula(
                ++id,
                "4Al+3O2===2Al2O3"
        );
        new ChemicalFormula(
                ++id,
                "2Mg+O2===2MgO",
                ReactCondition.LIGHT
        );
        new ChemicalFormula(
                ++id,
                "2Cu+O2===2CuO",
                ReactCondition.HEATING
        );
        new ChemicalFormula(
                ++id,
                "2H2+O2===2H2O",
                ReactCondition.LIGHT
        );
        new ChemicalFormula(
                ++id,
                "C+CO2===2CO",
                ReactCondition.HIGH_TEMPERATURE
        );
        new ChemicalFormula(
                ++id,
                "CO2+H2O===H2CO3"
        );
        new ChemicalFormula(
                ++id,
                "CaO+H2O===Ca(OH)_2"
        );
        new ChemicalFormula(
                ++id,
                "2CO+O2===2CO2",
                ReactCondition.LIGHT
        );
        new ChemicalFormula(
                ++id,
                "CaCO3+CO2+H2O===Ca(HCO3)_2"
        );
        new ChemicalFormula(
                ++id,
                "2Hg+O2===2HgO",
                ReactCondition.HEATING
        );
        new ChemicalFormula(
                ++id,
                "2HgO===2Hg+O2"
        );
        new ChemicalFormula(
                ++id,
                "2H2O2===2H2O+O2",
                ReactCondition.asCatalyzer(ChemicalCompounds.MnO2)
        );
        new ChemicalFormula(
                ++id,
                "2KMnO4===K2MnO4+MnO2+O2",
                ReactCondition.HEATING
        );
        new ChemicalFormula(
                ++id,
                "2KClO3===2KCl+3O2",
                ReactCondition.HEATING,
                ReactCondition.asCatalyzer(ChemicalCompounds.MnO2)
        );
        new ChemicalFormula(
                ++id,
                "2H2O===2H2+O2",
                ReactCondition.ELECTROLYSIS
        );
        new ChemicalFormula(
                ++id,
                "CaCO3===CaO+CO2",
                ReactCondition.HIGH_TEMPERATURE
        );
        new ChemicalFormula(
                ++id,
                "2NaHCO3===Na2CO3+H2O+CO2",
                ReactCondition.HEATING
        );
        new ChemicalFormula(
                ++id,
                "2H2O2===2H2O+O2"
        );
        new ChemicalFormula(
                ++id,
                "H2CO3===H2O+CO2"
        );
        new ChemicalFormula(
                ++id,
                "NH4HCO3===NH3+CO2+H2O"
        );
        new ChemicalFormula(
                ++id,
                "Ca(HCO3)_2===CaCO3+CO2+H2O"
        );
        new ChemicalFormula(
                ++id,
                "H2+CuO===Cu+H2O",
                ReactCondition.HEATING
        );
        new ChemicalFormula(
                ++id,
                "C+2CuO===2Cu+CO2",
                ReactCondition.HIGH_TEMPERATURE
        );
        new ChemicalFormula(
                ++id,
                "3C+2Fe2O3===4Fe+3CO2",
                ReactCondition.HIGH_TEMPERATURE
        );
        new ChemicalFormula(
                ++id,
                "C+H2O===H2+CO",
                ReactCondition.HIGH_TEMPERATURE
        );
        new ChemicalFormula(
                ++id,
                "Mg+2HCl===MgCl2+H2"
        );
        new ChemicalFormula(
                ++id,
                "Zn+2HCl===ZnCl2+H2"
        );
        new ChemicalFormula(
                ++id,
                "Fe+2HCl===FeCl2+H2"
        );
        new ChemicalFormula(
                ++id,
                "2Al+6HCl===2AlCl3+3H2"
        );
        new ChemicalFormula(
                ++id,
                "Mg+H2SO4===MgSO4+H2"
        );
        new ChemicalFormula(
                ++id,
                "Zn+H2SO4===ZnSO4+H2"
        );
        new ChemicalFormula(
                ++id,
                "Fe+H2SO4===FeSO4+H2"
        );
        new ChemicalFormula(
                ++id,
                "2Al+3H2SO4===Al2(SO4)_3+H2"
        );
        new ChemicalFormula(
                ++id,
                "3Mg+2AlCl3===3MgCl2+2Al"
        );
        new ChemicalFormula(
                ++id,
                "3Mg+Al2(SO4)_3===3MgSO4+2Al"
        );
        new ChemicalFormula(
                ++id,
                "3Mg+2Al(NO3)_3===3Mg(NO3)_2+2Al"
        );
        new ChemicalFormula(
                ++id,
                "Mg+ZnCl2===MgCl2+Zn"
        );
        new ChemicalFormula(
                ++id,
                "Mg+FeCl2===MgCl2+Fe"
        );
        new ChemicalFormula(
                ++id,
                "Mg+CuCl2===MgCl2+Cu"
        );
        new ChemicalFormula(
                ++id,
                "Mg+ZnSO4===MgSO4+Zn"
        );
        new ChemicalFormula(
                ++id,
                "Mg+FeSO4===MgSO4+Fe"
        );
        new ChemicalFormula(
                ++id,
                "Mg+CuSO4===MgSO4+Cu"
        );
        new ChemicalFormula(
                ++id,
                "Mg+Zn(NO3)_2===Mg(NO3)_2+Zn"
        );
        new ChemicalFormula(
                ++id,
                "Mg+Fe(NO3)_2===Mg(NO3)_2+Fe"
        );
        new ChemicalFormula(
                ++id,
                "Mg+Cu(NO3)_2===Mg(NO3)_2+Cu"
        );
        new ChemicalFormula(
                ++id,
                "Mg+2AgNO3===Mg(NO3)_2+2Ag"
        );
        new ChemicalFormula(
                ++id,
                "Zn+FeCl2===ZnCl2+Fe"
        );
        new ChemicalFormula(
                ++id,
                "Zn+CuCl2===ZnCl2+Cu"
        );
        new ChemicalFormula(
                ++id,
                "Zn+FeSO4===ZnSO4+Fe"
        );
        new ChemicalFormula(
                ++id,
                "Zn+CuSO4===ZnSO4+Cu"
        );
        new ChemicalFormula(
                ++id,
                "Zn+Fe(NO3)_2===Zn(NO3)_2+Fe"
        );
        new ChemicalFormula(
                ++id,
                "Zn+Cu(NO3)_2===Zn(NO3)_2+Cu"
        );
        new ChemicalFormula(
                ++id,
                "Zn+2AgNO3===Zn(NO3)_2+2Ag"
        );
        new ChemicalFormula(
                ++id,
                "Fe+CuCl2===FeCl2+Cu"
        );
        new ChemicalFormula(
                ++id,
                "Fe+CuSO4===FeSO4+Cu"
        );
        new ChemicalFormula(
                ++id,
                "Fe+Cu(NO3)_2===Fe(NO3)_2+Cu"
        );
        new ChemicalFormula(
                ++id,
                "Fe+2AgNO3===Fe(NO3)_2+2Ag"
        );
        new ChemicalFormula(
                ++id,
                "Cu+2AgNO3===Cu(NO3)_2+2Ag"
        );
        new ChemicalFormula(
                ++id,
                "2Mg+CO2===2MgO+C",
                ReactCondition.LIGHT
        );
        new ChemicalFormula(
                ++id,
                "3Fe+4H2O===Fe3O4+4H2",
                ReactCondition.HIGH_TEMPERATURE
        );
        new ChemicalFormula(
                ++id,
                "NaOH+HCl===NaCl+H2O"
        );
        new ChemicalFormula(
                ++id,
                "2NaOH+H2SO4===Na2SO4+2H2O"
        );
        new ChemicalFormula(
                ++id,
                "Ba(OH)_2+H2SO4===BaSO4+2H2O"
        );
        new ChemicalFormula(
                ++id,
                "Al(OH)_3+3HCl===AlCl3+3H2O"
        );
        new ChemicalFormula(
                ++id,
                "Mg(OH)_2+2HCl===MgCl2+2H2O"
        );
        new ChemicalFormula(
                ++id,
                "Ca(OH)_2+H2SO4===CaSO4+2H2O"
        );
        new ChemicalFormula(
                ++id,
                "Fe2O3+6HCl===2FeCl3+3H2O"
        );
        new ChemicalFormula(
                ++id,
                "Fe2O3+3H2SO4===Fe2(SO4)_3+3H2O"
        );
        new ChemicalFormula(
                ++id,
                "CuO+2HCl===CuCl2+H2O"
        );
        new ChemicalFormula(
                ++id,
                "CuO+H2SO4===CuSO4+H2O"
        );
        new ChemicalFormula(
                ++id,
                "CaCO3+2HCl===CaCl2+H2O+CO2"
        );
        new ChemicalFormula(
                ++id,
                "CaCO3+H2SO4===CaSO4+H2O+CO2"
        );
        new ChemicalFormula(
                ++id,
                "Na2CO3+2HCl===2NaCl+H2O+CO2"
        );
        new ChemicalFormula(
                ++id,
                "NaHCO3+HCl===NaCl+H2O+CO2"
        );
        new ChemicalFormula(
                ++id,
                "AgNO3+HCl===HNO3+AgCl"
        );
        new ChemicalFormula(
                ++id,
                "BaCl2+H2SO4===2HCl+BaSO4"
        );
        new ChemicalFormula(
                ++id,
                "Ba(NO3)_2+H2SO4===2HNO3+BaSO4"
        );
        new ChemicalFormula(
                ++id,
                "BaCO3+H2SO4===BaSO4+H2O+CO2"
        );
        new ChemicalFormula(
                ++id,
                "Ca(OH)_2+Na2CO3===2NaOH+CaCO3"
        );
        new ChemicalFormula(
                ++id,
                "NH4Cl+NaOH===NaCl+NH3+H2O"
        );
        new ChemicalFormula(
                ++id,
                "(NH4)_2SO4+2NaOH===Na2SO4+2NH3+2H2O"
        );
        new ChemicalFormula(
                ++id,
                "2NH4Cl+Ca(OH)_2===CaCl2+2NH3+2H2O"
        );
        new ChemicalFormula(
                ++id,
                "(NH4)_2SO4+Ca(OH)_2===CaSO4+2NH3+2H2O"
        );
        new ChemicalFormula(
                ++id,
                "CuSO4+2NaOH===Cu(OH)_2+Na2SO4"
        );
        new ChemicalFormula(
                ++id,
                "FeCl3+3NaOH===Fe(OH)_3+3NaCl"
        );
        new ChemicalFormula(
                ++id,
                "MgCl2+2NaOH===Mg(OH)_2+2NaCl"
        );
        new ChemicalFormula(
                ++id,
                "MgSO4+Ba(OH)_2===Mg(OH)_2+BaSO4"
        );
        new ChemicalFormula(
                ++id,
                "CuSO4+Ba(OH)_2===Cu(OH)_2+BaSO4"
        );
        new ChemicalFormula(
                ++id,
                "Fe2(SO4)_3+3Ba(OH)_2===2Fe(OH)_3+3BaSO4"
        );
        new ChemicalFormula(
                ++id,
                "CuSO4+Ca(OH)_2===CaSO4+Cu(OH)_2"
        );
        new ChemicalFormula(
                ++id,
                "NaCl+AgNO3===NaNO3+AgCl"
        );
        new ChemicalFormula(
                ++id,
                "CaCl2+Na2CO3===2NaCl+CaCO3"
        );
        new ChemicalFormula(
                ++id,
                "Ca(NO3)_2+Na2CO3===2NaNO3+CaCO3"
        );
        new ChemicalFormula(
                ++id,
                "BaCl2+Na2CO3===2NaCl+BaCO3"
        );
        new ChemicalFormula(
                ++id,
                "Ba(NO3)_2+Na2CO3===2NaNO3+BaCO3"
        );
        new ChemicalFormula(
                ++id,
                "BaCl2+Na2SO4===2NaCl+BaSO4"
        );
        new ChemicalFormula(
                ++id,
                "Ba(NO3)_2+Na2SO4===2NaNO3+BaSO4"
        );
        new ChemicalFormula(
                ++id,
                "Na2CO3+Ca(OH)_2===2NaOH+CaCO3"
        );
        new ChemicalFormula(
                ++id,
                "Ca(OH)_2+2NH4Cl===CaCl2+2H2O+2NH3"
        );
        new ChemicalFormula(
                ++id,
                "CO+CuO===Cu+CO2",
                ReactCondition.HEATING
        );
        new ChemicalFormula(
                ++id,
                "3CO+Fe2O3===2Fe+3CO2",
                ReactCondition.HIGH_TEMPERATURE
        );
        new ChemicalFormula(
                ++id,
                "CO+FeO===Fe+CO2",
                ReactCondition.HIGH_TEMPERATURE
        );
        new ChemicalFormula(
                ++id,
                "4CO+Fe3O4===3Fe+4CO2",
                ReactCondition.HIGH_TEMPERATURE
        );
        new ChemicalFormula(
                ++id,
                "CH4+2O2===CO2+2H2O",
                ReactCondition.LIGHT
        );
        new ChemicalFormula(
                ++id,
                "2CH4+3O2===2CO+4H2O",
                ReactCondition.LIGHT
        );
        new ChemicalFormula(
                ++id,
                "C2H5OH+3O2===2CO2+3H2O",
                ReactCondition.LIGHT
        );
        new ChemicalFormula(
                ++id,
                "C2H5OH+2O2===2CO+3H2O",
                ReactCondition.LIGHT
        );
        new ChemicalFormula(
                ++id,
                "Ca(OH)_2+CO2===CaCO3+H2O"
        );
        new ChemicalFormula(
                ++id,
                "2NaOH+CO2===Na2CO3+H2O"
        );
        new ChemicalFormula(
                ++id,
                "2NaOH+SO2===Na2SO3+H2O"
        );
        new ChemicalFormula(
                ++id,
                "2NaOH+SO3===Na2SO4+H2O"
        );
        new ChemicalFormula(
                ++id,
                "H2O+SO2===H2SO3"
        );
        new ChemicalFormula(
                ++id,
                "H2O+SO3===H2SO4"
        );
    }
}
