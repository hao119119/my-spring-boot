/**
 * @(#)CustomerSortRepository.java V1.2.0 16-9-2 
 * Copyright (c) 2014-2016 The inspur Software Foundation.All rights reserved 
 */
package com.example.myproject.domain;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Chen Hao
 * @version V1.2.0 16-9-2
 */
public interface CustomerSortRepository extends PagingAndSortingRepository<Customer, Long> {
}
