define(['./module'], function (app) {

	app.service('Constants', function() {
	    return{
	        ACCOUNT: {
	            TYPE: {
	                CHECKING_ACCOUNT: {
	                    id: 'CKA',
	                    name: 'Conta Corrente'
	                },
	                INVESTMENT_ACCOUNT: {
	                    id: 'INV',
	                    name: 'Conta Investmento'
	                },
	                STOCK_PORTFOLIO: {
	                    id: 'STK',
	                    name: 'Carteira de Ações'
	                }
	            },
	            INVESTMENT_PRODUCTS: {
	                INVESTMENT_FUND: {
	                    id: 'INVESTMENT_FUND',
	                    name: 'Fundo de Investimentos'
	                },
	                INVESTMENT_FUND_OF_SHARES: {
	                    id: 'INVESTMENT_FUND_OF_SHARES',
	                    name: 'Fundo de Acões'
	                },
	                INVESTMENT_FUND_PENSION: {
	                    id: 'INVESTMENT_FUND_PENSION',
	                    name: 'Fundo de Previdência'
	                }
	            },
	            LIQUIDITY_TYPE: {
	                DPLUS: {
	                    id: 'DPLUS',
	                    name: 'D+'
	                },
	                DUE_DATE: {
	                    id: 'DUE_DATE',
	                    name: 'No Vencimento'
	                }

	            },
	            INVESTMENT_RISK: {
	                HIGH: {
	                    id: 'HIGH',
	                    name: 'Alto'
	                },
	                MEDIUM: {
	                    id: 'MEDIUM',
	                    name: 'Médio'
	                },
	                LOW: {
	                    id: 'LOW',
	                    name: 'Baixo'
	                }
	            }
	        },
	        ACCOUNT_ENTRY: {
	            OPERATION: {
	                PURCHASE: {
	                    id: 'PURCHASE',
	                    name: 'Aplicação'
	                },
	                SALE: {
	                    id: 'SALE',
	                    name: 'Resgate'
	                },
	                IR_LAW: {
	                    id: 'IR_LAW',
	                    name: 'Come Cotas'
	                }
	            },
	            STOCK_OPERATION: {
	                PURCHASE: {
	                    id: 'PURCHASE',
	                    name: 'Compra'
	                },
	                SALE: {
	                    id: 'SALE',
	                    name: 'Venda'
	                }
	            }
	        }
	    }
	});
});

/*,
	                SAVING_ACCOUNT: {
	                    id: 'SVA',
	                    name: 'Conta Poupança'
	                },
	                CREDIT_ACCOUNT: {
	                    id: 'CCA',
	                    name: 'Cartão de Crédito'
	                },
	                VOUCHER_ACCOUNT: {
	                    id: 'VOA',
	                    name: 'Voucher'
	                },
	                LOAN_ACCOUNT: {
	                    id: 'LOA',
	                    name: 'Financiamento'
	                },
	                CONSUMER_GOODS_ACCOUNT: {
	                    id: 'CGA',
	                    name: 'Bens de Consumo'
	                }*/