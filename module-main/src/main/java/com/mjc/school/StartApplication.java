package com.mjc.school;

import com.mjc.school.operation.OperationEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("startApplication")
public class StartApplication {
    private OperationEngine operationEngine;
    @Autowired
    public StartApplication(OperationEngine operationEngine) {
        this.operationEngine = operationEngine;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true){
            operationEngine.printMenu();
            String string = scanner.nextLine();
            operationEngine.runOperations(string, scanner);
        }
    }
}
