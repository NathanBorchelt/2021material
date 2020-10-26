import { Injectable } from '@angular/core';
import { EndpointsService, CP_INTEGRATION_API, CP_UNISON_API } from './endpoints.service';
import { Observable, BehaviorSubject } from 'rxjs';
import { IntegratedDealer } from '../interfaces/IntegratedDealer';
import { IntegrationAccount } from '../interfaces/IntegrationAccount';
import { AuthService } from './auth.service';
import { DmsLookupModel } from '../interfaces/DmsLookup';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { CPResponse } from '../interfaces/CPResponse';
import { map } from 'rxjs/operators';
import { IntegrationLogResponse } from '../interfaces/IntegrationLogResponse';
import { IntegrationLogRequest } from '../interfaces/IntegrationLogRequest';

@Injectable({ providedIn: 'root' })
export class IntegrationService {

  private _integrationAccounts: BehaviorSubject<IntegrationAccount[]>;
  private integrationAccountStore: IntegrationAccount[];

  isSearching: boolean;
  startRange: number;
  endRange: number;
  searchedKey: string;
  searchContext: string;
  showSoldExtractLogs: boolean;

  constructor(private http: HttpClient, private endpoints: EndpointsService, private auth: AuthService) {
    this.showSoldExtractLogs = true;

    this.integrationAccountStore = [];
    this._integrationAccounts = new BehaviorSubject<IntegrationAccount[]>(this.integrationAccountStore);
  }


  getIntegrationLogs(request: IntegrationLogRequest) {
    return this.http.post(this.endpoints.build(CP_UNISON_API,
      `/v1/integration/getlogs`), request)
      .pipe(map((response: CPResponse<IntegrationLogResponse>) => {
        return response.data;
      }));
  }

  nextPage() {
    this.startRange = this.startRange + 50;
  }

  prevPage() {
    this.startRange = this.startRange - 50;
  }


  getLogFile(dealerId: string, filename: string): Observable<any> {
    return this.http.get(this.endpoints.build(CP_INTEGRATION_API,
      `/Logs/DownloadLogFile/${dealerId}/${filename}`),
      { headers: { 'Authorization': `Bearer ${this.auth.delegationToken}` }, responseType: 'text' })
      .pipe(map((response: string) => {
        // cleaning xml text
        let cleanedLogFile = response.slice(19);
        cleanedLogFile = cleanedLogFile.replace(/[\s]+(?![^><]*>)/g, '');
        cleanedLogFile = cleanedLogFile.trim();


        const parser = new DOMParser();
        const xmlDoc = parser.parseFromString(cleanedLogFile, 'text/xml');

        const json = JSON.parse(this.xml2json(xmlDoc, ''));
        return json;
      }));
  }

  getIntegratedDealers(orgId: number): Observable<IntegratedDealer[]> {
    return this.http.get(this.endpoints.build(CP_UNISON_API,
      `/v1/Integration/GetIntegratedDealersByOrg/${orgId}`),
      { observe: 'response' })
      .pipe(map((response: HttpResponse<CPResponse<IntegratedDealer[]>>) => {
        if (response.status > 299) {
          throw new Error(`Get integrated dealers failed: ${response.status}`);
        }
        return response.body.data;
      }));
  }


  getIntegratedDealer(dealerId: number): Observable<IntegratedDealer> {
    return this.http.get(this.endpoints.build(CP_UNISON_API,
      `/v1/Integration/GetIntegratedDealer/${dealerId}`),
      { observe: 'response' })
      .pipe(map((response: HttpResponse<CPResponse<IntegratedDealer>>) => {
        if (response.status > 299) {
          throw new Error(`Get integrated dealer failed: ${response.status}`);
        }
        return response.body.data;
      }));
  }


  validateDealer(linkedDmsId: string) {
    return this.http.post(this.endpoints.build(CP_INTEGRATION_API,
      `/Inventory/InstallNewDealer/${linkedDmsId}`), 0, { headers: { 'Authorization': `Bearer ${this.auth.delegationToken}` } })
      .map((response: CPResponse<boolean>) => response.data).share();
  }

  upsertDealer(dealer: any) {
    return this.http.post(this.endpoints.build(CP_UNISON_API, '/v1/Integration/UpsertDealer'), dealer, { observe: 'response' })
      .map((response: HttpResponse<CPResponse<IntegratedDealer>>) => response.body.data);
  }

  deleteIntegratedDealer(recordId: number) {
    return this.http.put(this.endpoints.build(CP_UNISON_API, `/v1/Integration/DeleteIntegratedDealer/${recordId}`), 0).share();
  }


  // ACCOUNTS
  getInventoryAccounts(dealerId: any): Observable<IntegrationAccount[]> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, `/v1/Integration/GetInventoryAccounts/${dealerId}`), { observe: 'response' })
      .pipe(map((response: HttpResponse<CPResponse<IntegrationAccount[]>>) => {
        if (response.status > 299) {
          throw new Error(`Get Inventory Accounts failed: ${response.status}`);
        }
        this.integrationAccountStore = response.body.data;
        this._integrationAccounts.next(this.integrationAccountStore);
        return this.integrationAccountStore;
      }));
  }

  insertInventoryAccount(account: IntegrationAccount) {
    return this.http.post(this.endpoints.build(CP_UNISON_API, '/v1/Integration/InsertInventoryAccount'), account)
      .map((response: CPResponse<IntegrationAccount>) => response.data).share();
  }

  updateInventoryAccount(obj: any) {
    this.http.put(this.endpoints.build(CP_UNISON_API, '/v1/Integration/UpdateInventoryAccount'), obj)
      .pipe(map((response: HttpResponse<null>) => {
        const index = this.integrationAccountStore.findIndex((x: any) => x.RecordId === obj.RecordId);
        this.integrationAccountStore[index].InventoryAccount = obj.InventoryAccount;
        this.integrationAccountStore[index].Description = obj.Description;
        this._integrationAccounts.next(this.integrationAccountStore);
      })).subscribe();
  }

  deleteInventoryAccount(recordId: any) {
    return this.http.delete(this.endpoints.build(CP_UNISON_API, `/v1/Integration/DeleteInventoryAccount/${recordId}`));
  }


  vehicleDmsLookup(intDealerId: number, vin: string): Observable<DmsLookupModel> {
    return this.http.get<CPResponse<DmsLookupModel>>(this.endpoints.build(CP_INTEGRATION_API,
      `/Inventory/VehicleLookupDMS/${intDealerId}/${vin}`),
      { headers: { 'Authorization': `Bearer ${this.auth.delegationToken}` } })
      .pipe(map((response: CPResponse<DmsLookupModel>) => response.data)).share();
  }


  getSalesTypes(): Observable<string[]> {
    return this.http.get(this.endpoints.build(CP_UNISON_API, '/v1/Integration/GetSalesTypes'))
      .pipe(map((response: CPResponse<string[]>) => response.data));
  }

  showFile(data) {
    const blob = new Blob([JSON.stringify(data, null, 2)], { type: 'text/plain' });
    const url = window.URL.createObjectURL(blob);
    window.open(url);
  }


  private xml2json(xml, tab: string) {
    const X = {
      toObj: (xml) => {
        let o = {};
        if (xml.nodeType === 1) {   // element node ..
          if (xml.attributes.length) {
            for (let i = 0; i < xml.attributes.length; i++) {
              o["@" + xml.attributes[i].nodeName] = (xml.attributes[i].nodeValue || "").toString();
            }
          }   // element with attributes  ..
          if (xml.firstChild) { // element has child nodes ..
            let textChild = 0, cdataChild = 0, hasElementChild = false;
            for (let n = xml.firstChild; n; n = n.nextSibling) {
              if (n.nodeType === 1) {
                hasElementChild = true;
              } else if (n.nodeType === 3 && n.nodeValue.match(/[^ \f\n\r\t\v]/)) {
                textChild++; // non-whitespace text
              } else if (n.nodeType === 4) {
                cdataChild++; // cdata section node
              }
            }
            if (hasElementChild) {
              if (textChild < 2 && cdataChild < 2) { // structured element with evtl. a single text or/and cdata node ..
                X.removeWhite(xml);
                for (let n = xml.firstChild; n; n = n.nextSibling) {
                  if (n.nodeType === 3) {
                    o["#text"] = X.escape(n.nodeValue);
                  } else if (n.nodeType === 4) {
                    o["#cdata"] = X.escape(n.nodeValue);
                  } else if (o[n.nodeName]) {  // multiple occurence of element ..
                    if (o[n.nodeName] instanceof Array) {
                      o[n.nodeName][o[n.nodeName].length] = X.toObj(n);
                    } else {
                      o[n.nodeName] = [o[n.nodeName], X.toObj(n)];
                    }
                  } else {
                    o[n.nodeName] = X.toObj(n);
                  }
                }
              } else { // mixed content
                if (!xml.attributes.length) {
                  o = X.escape(X.innerXml(xml));
                } else {
                  o["#text"] = X.escape(X.innerXml(xml));
                }
              }
            } else if (textChild) { // pure text
              if (!xml.attributes.length) {
                o = X.escape(X.innerXml(xml));
              } else {
                o["#text"] = X.escape(X.innerXml(xml));
              }
            } else if (cdataChild) { // cdata
              if (cdataChild > 1) {
                o = X.escape(X.innerXml(xml));
              } else {
                for (let n = xml.firstChild; n; n = n.nextSibling) {
                  o["#cdata"] = X.escape(n.nodeValue);
                }
              }
            }
          }
          if (!xml.attributes.length && !xml.firstChild) {
            o = null;
          }
        } else if (xml.nodeType === 9) { // document.node
          o = X.toObj(xml.documentElement);
        } else {
          alert("unhandled node type: " + xml.nodeType);
        }
        return o;
      },
      toJson: function (o, name, ind) {
        var json = name ? ("\"" + name + "\"") : "";
        if (o instanceof Array) {
          for (var i = 0, n = o.length; i < n; i++)
            o[i] = X.toJson(o[i], "", ind + "\t");
          json += (name ? ":[" : "[") + (o.length > 1 ? ("\n" + ind + "\t" + o.join(",\n" + ind + "\t") + "\n" + ind) : o.join("")) + "]";
        }
        else if (o == null)
          json += (name && ":") + "null";
        else if (typeof (o) == "object") {
          var arr = [];
          for (var m in o)
            arr[arr.length] = X.toJson(o[m], m, ind + "\t");
          json += (name ? ":{" : "{") + (arr.length > 1 ? ("\n" + ind + "\t" + arr.join(",\n" + ind + "\t") + "\n" + ind) : arr.join("")) + "}";
        }
        else if (typeof (o) == "string")
          json += (name && ":") + "\"" + o.toString() + "\"";
        else
          json += (name && ":") + o.toString();
        return json;
      },
      innerXml: function (node) {
        var s = ""
        if ("innerHTML" in node)
          s = node.innerHTML;
        else {
          var asXml = function (n) {
            var s = "";
            if (n.nodeType == 1) {
              s += "<" + n.nodeName;
              for (var i = 0; i < n.attributes.length; i++)
                s += " " + n.attributes[i].nodeName + "=\"" + (n.attributes[i].nodeValue || "").toString() + "\"";
              if (n.firstChild) {
                s += ">";
                for (var c = n.firstChild; c; c = c.nextSibling)
                  s += asXml(c);
                s += "</" + n.nodeName + ">";
              }
              else
                s += "/>";
            }
            else if (n.nodeType == 3)
              s += n.nodeValue;
            else if (n.nodeType == 4)
              s += "<![CDATA[" + n.nodeValue + "]]>";
            return s;
          };
          for (var c = node.firstChild; c; c = c.nextSibling)
            s += asXml(c);
        }
        return s;
      },
      escape: function (txt) {
        return txt.replace(/[\\]/g, "\\\\")
          .replace(/[\"]/g, '\\"')
          .replace(/[\n]/g, '\\n')
          .replace(/[\r]/g, '\\r');
      },
      removeWhite: function (e) {
        e.normalize();
        for (var n = e.firstChild; n;) {
          if (n.nodeType == 3) {  // text node
            if (!n.nodeValue.match(/[^ \f\n\r\t\v]/)) { // pure whitespace text node
              var nxt = n.nextSibling;
              e.removeChild(n);
              n = nxt;
            }
            else
              n = n.nextSibling;
          }
          else if (n.nodeType == 1) {  // element node
            X.removeWhite(n);
            n = n.nextSibling;
          }
          else                      // any other node
            n = n.nextSibling;
        }
        return e;
      }
    };
    if (xml.nodeType == 9) // document node
      xml = xml.documentElement;
    var json = X.toJson(X.toObj(X.removeWhite(xml)), xml.nodeName, "\t");
    return "{\n" + tab + (tab ? json.replace(/\t/g, tab) : json.replace(/\t|\n/g, "")) + "\n}";
  }
}
