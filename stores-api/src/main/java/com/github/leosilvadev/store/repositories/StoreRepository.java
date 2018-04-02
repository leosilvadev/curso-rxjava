package com.github.leosilvadev.store.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.github.leosilvadev.store.domains.Store;

@Repository
public interface StoreRepository extends PagingAndSortingRepository<Store, String> {

}
