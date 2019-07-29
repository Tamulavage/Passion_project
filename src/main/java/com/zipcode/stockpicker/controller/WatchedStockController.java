package com.zipcode.stockpicker.controller;


import com.zipcode.stockpicker.model.Indicator;
import com.zipcode.stockpicker.model.WatchedStock;
import com.zipcode.stockpicker.services.WatchedStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/watch")
public class WatchedStockController {

    private WatchedStockService watchedStockService;

    @Autowired
    public WatchedStockController(WatchedStockService service) {watchedStockService = service;}

    @PostMapping("/new")
    public ResponseEntity<WatchedStock> addWatchedStock(@RequestBody WatchedStock watchedStock){
        return new ResponseEntity<>(watchedStockService.watchNewStock(watchedStock), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<WatchedStock>> getWatchedStock(){
        return new ResponseEntity<>(watchedStockService.getWatchedStocks(), HttpStatus.OK);
    }

    // TODO: remove in future
    @GetMapping("/refresh/{id}")
    public ResponseEntity<List<WatchedStock>> getRecentStockValues(@PathVariable Integer id){
      //  return new ResponseEntity<>(watchedStockService.getRecentStockValues(id), HttpStatus.OK);
      return null;
    }

    @GetMapping("/analyzeWatchedStocks/")
    public ResponseEntity<List<Indicator>> analyzeWatchedStocks(){
     //   return new ResponseEntity<>(watchedStockService.analyzeWatchedStocks(), HttpStatus.OK);
     // TODO: complete once single is populated
     return null;
    }

    @GetMapping("/analyzeWatchedStock/{id}")
    public ResponseEntity<Indicator> analyzeWatchedStocks(@PathVariable Integer id){
        return new ResponseEntity<>(watchedStockService.analyzeWatchedStock(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WatchedStock> deleteWatchedStock(@PathVariable Integer id){
        //return new ResponseEntity<>(watchedStockService.deleteWatchedStocks(id), HttpStatus.OK);
        watchedStockService.deleteWatchedStocks(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/end/{id}")
    public ResponseEntity<WatchedStock> updateEndDate(@PathVariable Integer id){
        return new ResponseEntity<>(watchedStockService.stopWatchingStock(id), HttpStatus.OK);
    }
}
