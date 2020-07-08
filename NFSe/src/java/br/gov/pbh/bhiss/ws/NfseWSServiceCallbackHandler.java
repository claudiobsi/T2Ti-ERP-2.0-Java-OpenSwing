
/**
 * NfseWSServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package br.gov.pbh.bhiss.ws;

    /**
     *  NfseWSServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class NfseWSServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public NfseWSServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public NfseWSServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for cancelarNfse method
            * override this method for handling normal response from cancelarNfse operation
            */
           public void receiveResultcancelarNfse(
                    br.gov.pbh.bhiss.ws.NfseWSServiceStub.CancelarNfseResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cancelarNfse operation
           */
            public void receiveErrorcancelarNfse(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarLoteRps method
            * override this method for handling normal response from consultarLoteRps operation
            */
           public void receiveResultconsultarLoteRps(
                    br.gov.pbh.bhiss.ws.NfseWSServiceStub.ConsultarLoteRpsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarLoteRps operation
           */
            public void receiveErrorconsultarLoteRps(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarSituacaoLoteRps method
            * override this method for handling normal response from consultarSituacaoLoteRps operation
            */
           public void receiveResultconsultarSituacaoLoteRps(
                    br.gov.pbh.bhiss.ws.NfseWSServiceStub.ConsultarSituacaoLoteRpsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarSituacaoLoteRps operation
           */
            public void receiveErrorconsultarSituacaoLoteRps(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for gerarNfse method
            * override this method for handling normal response from gerarNfse operation
            */
           public void receiveResultgerarNfse(
                    br.gov.pbh.bhiss.ws.NfseWSServiceStub.GerarNfseResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from gerarNfse operation
           */
            public void receiveErrorgerarNfse(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarNfsePorFaixa method
            * override this method for handling normal response from consultarNfsePorFaixa operation
            */
           public void receiveResultconsultarNfsePorFaixa(
                    br.gov.pbh.bhiss.ws.NfseWSServiceStub.ConsultarNfsePorFaixaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarNfsePorFaixa operation
           */
            public void receiveErrorconsultarNfsePorFaixa(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarNfsePorRps method
            * override this method for handling normal response from consultarNfsePorRps operation
            */
           public void receiveResultconsultarNfsePorRps(
                    br.gov.pbh.bhiss.ws.NfseWSServiceStub.ConsultarNfsePorRpsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarNfsePorRps operation
           */
            public void receiveErrorconsultarNfsePorRps(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarNfse method
            * override this method for handling normal response from consultarNfse operation
            */
           public void receiveResultconsultarNfse(
                    br.gov.pbh.bhiss.ws.NfseWSServiceStub.ConsultarNfseResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarNfse operation
           */
            public void receiveErrorconsultarNfse(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for recepcionarLoteRps method
            * override this method for handling normal response from recepcionarLoteRps operation
            */
           public void receiveResultrecepcionarLoteRps(
                    br.gov.pbh.bhiss.ws.NfseWSServiceStub.RecepcionarLoteRpsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from recepcionarLoteRps operation
           */
            public void receiveErrorrecepcionarLoteRps(java.lang.Exception e) {
            }
                


    }
    