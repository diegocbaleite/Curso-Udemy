package br.com.leite.controllers;

import br.com.leite.exception.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    // http://localhost:8080/math/sum/3/5
    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable String numberOne,
                      @PathVariable String numberTwo) {

        Double n1 = parse(numberOne);
        Double n2 = parse(numberTwo);

        return n1 + n2;
    }

    // http://localhost:8080/math/subtraction/10/5
    @RequestMapping("/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(@PathVariable String numberOne,
                              @PathVariable String numberTwo) {

        Double n1 = parse(numberOne);
        Double n2 = parse(numberTwo);

        return n1 - n2;
    }

    // http://localhost:8080/math/division/10/5
    @RequestMapping("/division/{numberOne}/{numberTwo}")
    public Double division(@PathVariable String numberOne,
                           @PathVariable String numberTwo) {

        Double n1 = parse(numberOne);
        Double n2 = parse(numberTwo);

        if (n2 == 0) {
            throw new UnsupportedMathOperationException("Não é possível dividir por zero!");
        }

        return n1 / n2;
    }

    // http://localhost:8080/math/multiplication/3/5
    @RequestMapping("/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(@PathVariable String numberOne,
                                 @PathVariable String numberTwo) {

        Double n1 = parse(numberOne);
        Double n2 = parse(numberTwo);

        return n1 * n2;
    }

    private Double parse(String strNumber) {
        if (!isNumeric(strNumber)) {
            throw new UnsupportedMathOperationException("Por favor, insira um valor numérico!");
        }

        String number = strNumber.replace(",", ".");
        return Double.parseDouble(number);
    }

    private boolean isNumeric(String strNumber) {
        if (strNumber == null || strNumber.isEmpty()) return false;

        String number = strNumber.replace(",", ".");

        // Aceita números como: 10, -10, 10.5, -10.5
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}