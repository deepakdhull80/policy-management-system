package com.imo.ui.service;

import org.springframework.stereotype.Service;

import com.imo.ui.modal.QuotesMaster;
@Service
public interface QuoteService {

	boolean addQuoteMaster(QuotesMaster quoteMaster, String token);

}
