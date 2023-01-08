package org.spring.service;

import org.spring.entity.Product;
import org.spring.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Transactional // transaction start // to enable the work of @Transactional annotation, we should enable Transaction Manager, and use @EnableTransactionManagement
//    @Transactional(rollbackFor = Exception.class) // rollback for any Exception class, where by default @Transactional not rollback for check exception like Exception
//    @Transactional(noRollbackFor = RuntimeException.class) // no rollback for any RuntimeException class, where by default @Transactional rollback for unchecked exception like RuntimeException
    public void saveProductInfo() {
        try {
            // creating product
            for(int i=1;i<=10;i++) {
                Product product = new Product(i, "Test Product: " + i);
                System.out.println("Product : " + product);

                productRepo.saveProduct(product);

                // problem without @Transaction annotation
                if(i == 7) { // so first 7 product will store in the DB, then it will throw the exception and program exits
                    // so it breaks the atomicity rules of ACID properties of DBMS || Atomicity : The entire transaction takes place at once, or doesn't happen at all
                    // suppose, a transaction is happening from account bank account to another bank account, then it should be transacted properly
                    // so, we should put @Transactional annotation on top of the main logic of transaction
                    throw new RuntimeException("Database is down !!");
                }
            } // transaction committed/rolledback (means end here)
        } catch (RuntimeException runtimeException) {
            System.out.println(runtimeException.getMessage());

            // @Transactional not working properly with try catch block for rollback operation
            // to activate rollback for any error in @Transactional, we should handle it programmatically by the help of TransactionAspectSupport interceptor
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        System.out.println("==** Program Ends **==");
    }
}
