package org.irmc.industrialrevival.api.elements.reaction;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalFormula;
import org.irmc.industrialrevival.api.items.attributes.ChemReactable;
import org.irmc.industrialrevival.api.machines.process.Environment;
import org.irmc.industrialrevival.core.services.IRRegistry;
import org.irmc.industrialrevival.utils.JavaUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReactHelperTest {
    private Environment mockEnvironment;
    private ReactCondition mockCondition;
    private ChemicalCompound mockCompound1;
    private ChemicalCompound mockCompound2;
    private ChemicalFormula mockFormula;

    @BeforeEach
    void setUp() {
        mockEnvironment = mock(Environment.class);
        mockCondition = mock(ReactCondition.class);
        mockCompound1 = mock(ChemicalCompound.class);
        mockCompound2 = mock(ChemicalCompound.class);
        mockFormula = mock(ChemicalFormula.class);

        // 设置默认行为
        when(mockCompound1.getMolarMass()).thenReturn(10.0);
        when(mockCompound2.getMolarMass()).thenReturn(20.0);
    }

    @Test
    void testReactWithItemStackArray() {
        // 准备测试数据
        ItemStack mockItem = mock(ItemStack.class);
        ChemReactable mockReactable = mock(ChemReactable.class);

        when(mockReactable.getChemicalCompound(mockItem)).thenReturn(mockCompound1);
        when(mockReactable.getMass(mockItem)).thenReturn(100.0);

        Set<ReactCondition> conditions = new HashSet<>();
        conditions.add(mockCondition);

        try (MockedStatic<IRRegistry> irRegistryMockedStatic = mockStatic(IRRegistry.class);
             MockedStatic<JavaUtil> javaUtilMockedStatic = mockStatic(JavaUtil.class)) {

            IRRegistry mockRegistry = mock(IRRegistry.class);
            when(mockRegistry.getChemicalFormulas()).thenReturn(Collections.singletonMap(1, mockFormula));
            irRegistryMockedStatic.when(IRRegistry::getInstance).thenReturn(mockRegistry);

            when(mockFormula.getConditions()).thenReturn(conditions);
            when(mockFormula.getInput()).thenReturn(Collections.singletonMap(mockCompound1, 1));
            when(mockFormula.getOutput()).thenReturn(Collections.singletonMap(mockCompound2, 1));

            javaUtilMockedStatic.when(() -> JavaUtil.shuffle(anyList())).thenAnswer(invocation -> invocation.getArgument(0));

            ReactResult result = ReactHelper.react(mockEnvironment, conditions, mockItem);

            assertNotNull(result);
            assertNotEquals(ReactResult.FAILED, result);
        }
    }

    @Test
    void testReactWithList() {
        // 准备测试数据
        ItemStack mockItem = mock(ItemStack.class);
        ChemReactable mockReactable = mock(ChemReactable.class);

        when(mockReactable.getChemicalCompound(mockItem)).thenReturn(mockCompound1);
        when(mockReactable.getMass(mockItem)).thenReturn(100.0);

        Set<ReactCondition> conditions = new HashSet<>();
        conditions.add(mockCondition);

        try (MockedStatic<IRRegistry> irRegistryMockedStatic = mockStatic(IRRegistry.class);
             MockedStatic<JavaUtil> javaUtilMockedStatic = mockStatic(JavaUtil.class)) {

            IRRegistry mockRegistry = mock(IRRegistry.class);
            when(mockRegistry.getChemicalFormulas()).thenReturn(Collections.singletonMap(1, mockFormula));
            irRegistryMockedStatic.when(IRRegistry::getInstance).thenReturn(mockRegistry);

            when(mockFormula.getConditions()).thenReturn(conditions);
            when(mockFormula.getInput()).thenReturn(Collections.singletonMap(mockCompound1, 1));
            when(mockFormula.getOutput()).thenReturn(Collections.singletonMap(mockCompound2, 1));

            javaUtilMockedStatic.when(() -> JavaUtil.shuffle(anyList())).thenAnswer(invocation -> invocation.getArgument(0));

            ReactResult result = ReactHelper.react(mockEnvironment, conditions, Collections.singletonList(mockItem));

            assertNotNull(result);
            assertNotEquals(ReactResult.FAILED, result);
        }
    }

    @Test
    void testReactWithMap() {
        // 准备测试数据
        ChemReactable mockReactable = mock(ChemReactable.class);
        ItemStack mockItem = mock(ItemStack.class);

        when(mockReactable.getChemicalCompound(mockItem)).thenReturn(mockCompound1);
        when(mockReactable.getMass(mockItem)).thenReturn(100.0);

        Set<ReactCondition> conditions = new HashSet<>();
        conditions.add(mockCondition);

        try (MockedStatic<IRRegistry> irRegistryMockedStatic = mockStatic(IRRegistry.class);
             MockedStatic<JavaUtil> javaUtilMockedStatic = mockStatic(JavaUtil.class)) {

            IRRegistry mockRegistry = mock(IRRegistry.class);
            when(mockRegistry.getChemicalFormulas()).thenReturn(Collections.singletonMap(1, mockFormula));
            irRegistryMockedStatic.when(IRRegistry::getInstance).thenReturn(mockRegistry);

            when(mockFormula.getConditions()).thenReturn(conditions);
            when(mockFormula.getInput()).thenReturn(Collections.singletonMap(mockCompound1, 1));
            when(mockFormula.getOutput()).thenReturn(Collections.singletonMap(mockCompound2, 1));

            javaUtilMockedStatic.when(() -> JavaUtil.shuffle(anyList())).thenAnswer(invocation -> invocation.getArgument(0));

            Map<ChemReactable, ItemStack> reactables = new HashMap<>();
            reactables.put(mockReactable, mockItem);

            ReactResult result = ReactHelper.react(mockEnvironment, conditions, reactables);

            assertNotNull(result);
            assertNotEquals(ReactResult.FAILED, result);
        }
    }

    @Test
    void testReact0_NoMatchingFormula() {
        // 准备测试数据
        Set<ReactCondition> conditions = new HashSet<>();
        conditions.add(mockCondition);

        Map<ChemicalCompound, Double> reactants = new HashMap<>();
        reactants.put(mockCompound1, 100.0);

        try (MockedStatic<IRRegistry> irRegistryMockedStatic = mockStatic(IRRegistry.class)) {
            IRRegistry mockRegistry = mock(IRRegistry.class);
            when(mockRegistry.getChemicalFormulas()).thenReturn(Collections.emptyMap());
            irRegistryMockedStatic.when(IRRegistry::getInstance).thenReturn(mockRegistry);

            ReactResult result = ReactHelper.react0(mockEnvironment, conditions, reactants);

            assertEquals(ReactResult.FAILED, result);
        }
    }

    @Test
    void testReactBalanced() {
        // 准备测试数据
        Set<ReactCondition> conditions = new HashSet<>();
        conditions.add(mockCondition);

        Map<ChemicalCompound, Double> reactants = new HashMap<>();
        reactants.put(mockCompound1, 100.0);

        try (MockedStatic<IRRegistry> irRegistryMockedStatic = mockStatic(IRRegistry.class);
             MockedStatic<JavaUtil> javaUtilMockedStatic = mockStatic(JavaUtil.class)) {

            IRRegistry mockRegistry = mock(IRRegistry.class);
            when(mockRegistry.getChemicalFormulas()).thenReturn(Collections.singletonMap(1, mockFormula));
            irRegistryMockedStatic.when(IRRegistry::getInstance).thenReturn(mockRegistry);

            when(mockFormula.getConditions()).thenReturn(conditions);
            when(mockFormula.getInput()).thenReturn(Collections.singletonMap(mockCompound1, 1));
            when(mockFormula.getOutput()).thenReturn(Collections.singletonMap(mockCompound2, 1));

            javaUtilMockedStatic.when(() -> JavaUtil.shuffle(anyList())).thenAnswer(invocation -> invocation.getArgument(0));

            List<ReactResult> results = ReactHelper.reactBalanced(mockEnvironment, conditions, reactants);

            assertNotNull(results);
            assertFalse(results.isEmpty());
        }
    }

    @Test
    void testConditionSatisfied() {
        // 准备测试数据
        Set<ReactCondition> requiredConditions = new HashSet<>();
        Set<ReactCondition> currentConditions = new HashSet<>();

        requiredConditions.add(mockCondition);
        currentConditions.add(mockCondition);

        Map<ChemicalCompound, Double> masses = new HashMap<>();
        masses.put(mockCompound1, 100.0);

        // 测试基本条件满足
        assertTrue(ReactHelper.conditionSatisfied(mockEnvironment, masses, requiredConditions, currentConditions));

        // 测试缺少必要条件
        currentConditions.clear();
        assertFalse(ReactHelper.conditionSatisfied(mockEnvironment, masses, requiredConditions, currentConditions));

        // 测试催化剂条件
        ReactCondition catalystCondition = mock(ReactCondition.class);
        when(catalystCondition.getType()).thenReturn(ReactCondition.Type.CATALYZER);
        when(catalystCondition.getCatalyst()).thenReturn(mockCompound1);

        requiredConditions.add(catalystCondition);
        currentConditions.clear();

        // 应该失败，因为当前条件中没有催化剂条件
        assertFalse(ReactHelper.conditionSatisfied(mockEnvironment, masses, requiredConditions, currentConditions));

        // 应该成功，因为虽然没有条件，但存在对应的化合物
        currentConditions.add(catalystCondition);
        assertTrue(ReactHelper.conditionSatisfied(mockEnvironment, masses, requiredConditions, currentConditions));
    }

    @Test
    void testInputsSatisfied() {
        // 准备测试数据
        Map<ChemicalCompound, Integer> inputs = new HashMap<>();
        Set<ChemicalCompound> current = new HashSet<>();

        inputs.put(mockCompound1, 1);
        inputs.put(mockCompound2, 1);

        current.add(mockCompound1);

        // 测试缺少一个输入
        assertFalse(ReactHelper.inputsSatisfied(mockEnvironment, inputs, current));

        // 测试所有输入都满足
        current.add(mockCompound2);
        assertTrue(ReactHelper.inputsSatisfied(mockEnvironment, inputs, current));
    }

    @Test
    void testCalculateOutput_NormalCase() {
        // 准备测试数据
        Set<ReactCondition> conditions = new HashSet<>();
        conditions.add(mockCondition);

        Map<ChemicalCompound, Integer> inputs = new HashMap<>();
        inputs.put(mockCompound1, 2);

        Map<ChemicalCompound, Double> reactants = new HashMap<>();
        reactants.put(mockCompound1, 100.0);

        Map<ChemicalCompound, Integer> outputs = new HashMap<>();
        outputs.put(mockCompound2, 1);

        when(mockFormula.getInput()).thenReturn(inputs);
        when(mockFormula.getOutput()).thenReturn(outputs);
        when(mockFormula.getConditions()).thenReturn(conditions);

        ReactResult result = ReactHelper.calculateOutput(mockEnvironment, conditions, reactants, mockFormula);

        assertNotNull(result);
        assertNotEquals(ReactResult.FAILED, result);

        // 验证消耗量
        Map<ChemicalCompound, Double> consumed = result.getConsume();
        assertEquals(1, consumed.size());
        assertTrue(consumed.containsKey(mockCompound1));
        double consumedMass = consumed.get(mockCompound1);
        assertTrue(consumedMass > 0 && consumedMass <= 100.0);

        // 验证产出
        Map<ChemicalCompound, Double> produced = result.getProduce();
        assertEquals(1, produced.size());
        assertTrue(produced.containsKey(mockCompound2));
        double producedMass = produced.get(mockCompound2);
        assertTrue(producedMass > 0);
    }

    @Test
    void testCalculateOutput_ReactAll() {
        // 准备测试数据
        Set<ReactCondition> conditions = new HashSet<>();
        conditions.add(mockCondition);

        Map<ChemicalCompound, Integer> inputs = new HashMap<>();
        inputs.put(mockCompound1, 2);

        Map<ChemicalCompound, Double> reactants = new HashMap<>();
        reactants.put(mockCompound1, 100.0);

        Map<ChemicalCompound, Integer> outputs = new HashMap<>();
        outputs.put(mockCompound2, 1);

        when(mockFormula.getInput()).thenReturn(inputs);
        when(mockFormula.getOutput()).thenReturn(outputs);
        when(mockFormula.getConditions()).thenReturn(conditions);

        ReactResult result = ReactHelper.calculateOutput(mockEnvironment, conditions, reactants, mockFormula, true);

        assertNotNull(result);
        assertNotEquals(ReactResult.FAILED, result);

        // 验证消耗量
        Map<ChemicalCompound, Double> consumed = result.getConsume();
        assertEquals(1, consumed.size());
        assertTrue(consumed.containsKey(mockCompound1));
        assertEquals(100.0, consumed.get(mockCompound1), 0.001);

        // 验证产出
        Map<ChemicalCompound, Double> produced = result.getProduce();
        assertEquals(1, produced.size());
        assertTrue(produced.containsKey(mockCompound2));
        double producedMass = produced.get(mockCompound2);
        assertTrue(producedMass > 0);
    }

    @Test
    void testCalculateOutput_NullMaxProportion() {
        // 准备测试数据
        Set<ReactCondition> conditions = new HashSet<>();
        conditions.add(mockCondition);

        Map<ChemicalCompound, Integer> inputs = new HashMap<>();
        inputs.put(mockCompound1, 2);

        Map<ChemicalCompound, Double> reactants = new HashMap<>();
        reactants.put(mockCompound1, 100.0);

        Map<ChemicalCompound, Integer> outputs = new HashMap<>();
        outputs.put(mockCompound2, 1);

        when(mockFormula.getInput()).thenReturn(inputs);
        when(mockFormula.getOutput()).thenReturn(outputs);
        when(mockFormula.getConditions()).thenReturn(conditions);
        when(mockFormula.getConditionSensor()).thenReturn((env, cond, prop) -> null);  // 返回null

        ReactResult result = ReactHelper.calculateOutput(mockEnvironment, conditions, reactants, mockFormula);

        assertEquals(ReactResult.FAILED, result);
    }

    @Test
    void testReactAll_Success() {
        // 准备测试数据
        Set<ReactCondition> conditions = new HashSet<>();
        conditions.add(mockCondition);

        Map<ChemicalCompound, Integer> inputs = new HashMap<>();
        inputs.put(mockCompound1, 2);

        Map<ChemicalCompound, Double> reactants = new HashMap<>();
        reactants.put(mockCompound1, 100.0);

        Map<ChemicalCompound, Integer> outputs = new HashMap<>();
        outputs.put(mockCompound2, 1);

        when(mockFormula.getInput()).thenReturn(inputs);
        when(mockFormula.getOutput()).thenReturn(outputs);
        when(mockFormula.getConditions()).thenReturn(conditions);

        ReactResult result = ReactHelper.reactAll(mockEnvironment, conditions, reactants, mockFormula);

        assertNotNull(result);
        assertNotEquals(ReactResult.FAILED, result);
    }

    @Test
    void testReactAll_FailureDueToConditions() {
        // 准备测试数据
        Set<ReactCondition> conditions = new HashSet<>();

        Map<ChemicalCompound, Integer> inputs = new HashMap<>();
        inputs.put(mockCompound1, 2);

        Map<ChemicalCompound, Double> reactants = new HashMap<>();
        reactants.put(mockCompound1, 100.0);

        Map<ChemicalCompound, Integer> outputs = new HashMap<>();
        outputs.put(mockCompound2, 1);

        when(mockFormula.getInput()).thenReturn(inputs);
        when(mockFormula.getOutput()).thenReturn(outputs);

        Set<ReactCondition> requiredConditions = new HashSet<>();
        requiredConditions.add(mockCondition);
        when(mockFormula.getConditions()).thenReturn(requiredConditions);

        ReactResult result = ReactHelper.reactAll(mockEnvironment, conditions, reactants, mockFormula);

        assertEquals(ReactResult.FAILED, result);
    }

    @Test
    void testReactAll_FailureDueToInputs() {
        // 准备测试数据
        Set<ReactCondition> conditions = new HashSet<>();
        conditions.add(mockCondition);

        Map<ChemicalCompound, Integer> inputs = new HashMap<>();
        inputs.put(mockCompound2, 2);  // 需要compound2

        Map<ChemicalCompound, Double> reactants = new HashMap<>();
        reactants.put(mockCompound1, 100.0);  // 只有compound1

        Map<ChemicalCompound, Integer> outputs = new HashMap<>();
        outputs.put(mockCompound2, 1);

        when(mockFormula.getInput()).thenReturn(inputs);
        when(mockFormula.getOutput()).thenReturn(outputs);
        when(mockFormula.getConditions()).thenReturn(conditions);

        ReactResult result = ReactHelper.reactAll(mockEnvironment, conditions, reactants, mockFormula);

        assertEquals(ReactResult.FAILED, result);
    }
}
