package com.example.demo.healthy;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorTest {

    @BeforeEach
    void setUp() {
        System.out.println("Before each unit test");
    }

    @AfterEach
    void tearDown() {
        System.out.println("After each unit test");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all unit test");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all unit test");
    }

    @Nested
    class IsDietRecommended {

        /**
         * Truyền nhiều bộ param chứa nhiều field vào test
         * C2
         * Sử dụng 2 annotation @ParameterizedTest và  @CsvSource để khai báo param
         * Lưu ý phải khai báo value dưới dạng String
         * các value của 1 bộ cách nhau = dấu ,
         * các bộ value cũng cách nhau bởi dấu ,
         * Cách này khác c1 ở chỗ khai báo tên field 1 cách tường minh
         */
        @ParameterizedTest(name = "weight={0}, height={1}")
        @CsvSource(value = {"70.0, 1.72", "50, 1.50", "89.0, 1.60"})
        void should_ReturnTrue_When_DietRecommended(Double coderWeight, Double coderHeight) {
            // given - các điều kiện ban đầu, input
            double weight = coderWeight;
            double height = coderHeight;

            // when - nơi gọi phương thức, thường lưu kết quả vào 1 biến
            boolean recommended = BMICalculator.isDietRecommended(weight, height);

            // then
            assertTrue(recommended);
        }

        /** Truyền nhiều bộ param chứa nhiều field vào test
         *  C1
         *  Sử dụng 2 annotation @ParameterizedTest và  @CsvSource để khai báo param
         *  Lưu ý phải khai báo value dưới dạng String
         *  các value của 1 bộ cách nhau = dấu ,
         *  các bộ value cũng cách nhau bởi dấu ,
         */
//    @ParameterizedTest
//    @CsvSource(value =  {"70.0, 1.72", "50, 1.50", "89.0, 1.60"})
//    void should_ReturnTrue_When_DietRecommended(Double coderWeight, Double coderHeight) {
//        // given - các điều kiện ban đầu, input
//        double weight = coderWeight;
//        double height = coderHeight;
//
//        // when - nơi gọi phương thức, thường lưu kết quả vào 1 biến
//        boolean recommended = BMICalculator.isDietRecommended(weight, height);
//
//        // then
//        assertTrue(recommended);
//    }


        /**
         * Truyền nhiều bộ param chứa nhiều field vào test
         * C3
         * Sử dụng 2 annotation @ParameterizedTest và  @CsvFileSource để khai báo param
         * SD attribute resources để khai báo đường dẫn đến file chứa data test
         */


//    /**
//     * Truyền nhiều bộ param chứa 1 field vào test
//     * Sử dụng 2 annotation @ParameterizedTest và  @ValueSource để khai báo param
//     */
//    @ValueSource(doubles = {70.0, 50, 89.0, 95.0, 110})
//    @ParameterizedTest
//    void should_ReturnTrue_When_DietRecommended(Double coderWeight) {
//        double weight = coderWeight;
//        double height = 1.82;
//
//        boolean recommended = BMICalculator.isDietRecommended(weight, height);
//
//        assertTrue(recommended);
//    }
        @Test
        void should_ReturnFalse_When_DietNotRecommended() {
            // given - các điều kiện ban đầu, input
            double weight = 50.0;
            double height = 1.92;

            // when - nơi gọi phương thức, thường lưu kết quả vào 1 biến
            boolean recommended = BMICalculator.isDietRecommended(weight, height);

            // then
            assertFalse(recommended);
        }

        // Sử dụng assertThrows và Executable để test throw 1 ngoại lệ
        @Test
        void should_ThrowArithmeticException_When_HeightZero() {
            // given - các điều kiện ban đầu, input
            double weight = 50.0;
            double height = 0.0;

            // when - nơi gọi phương thức, thường lưu kết quả vào 1 biến
            Executable executable = () -> BMICalculator.isDietRecommended(weight, height);

            // then
            assertThrows(ArithmeticException.class, executable);
        }

    }

    // Sử dụng assertAll khi muốn assert nhiều
    @Test
    void should_ReturnCoderWithWorstBMI_When_CoderListNotEmpty() {

        List<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.52, 60.0));
        coders.add(new Coder(1.72, 50.0));
        coders.add(new Coder(1.62, 50.0));

        Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

        assertAll(
                () -> assertEquals(1.50, coderWorstBMI.getHeight()),
                () -> assertEquals(54.0, coderWorstBMI.getWeight())
        );
    }


    // Sử dụng assertNull để test null
    @Test
    void should_ReturnNullWorstBMI_When_CoderListEmpty() {

        List<Coder> coders = new ArrayList<>();

        Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

        assertNull(coderWorstBMI);
    }

    // assertArrayEquals để assert mảng
    @Test
    void should_ReturnCorrectBMIScoreArray_WhenCoderListNotEmpty() {
        List<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.80, 60.0));
        coders.add(new Coder(1.82, 98.0));
        coders.add(new Coder(1.82, 64.7));
        double[] expected = {18.52, 29.59, 19.53};

        double[] bmiScores = BMICalculator.getBMIScores(coders);

        assertArrayEquals(expected, bmiScores);
    }

    @Test
    void should_ReturnCoderWorstBMIIn1Ms_When_CoderListHas10000Elements() {
        // given
        List<Coder> coders = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            coders.add(new Coder(1 + i, 10 + i));
        }

        // when
        Executable excutable = () -> BMICalculator.findCoderWithWorstBMI(coders);

        // then
        assertTimeout(Duration.ofMillis(40), excutable);

    }
}