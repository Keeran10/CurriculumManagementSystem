import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private httpClient: HttpClient) { }

  public getFeatureFlagTest() {
    return this.httpClient.get<string>('http://192.168.99.100:8080/featureFlagTest');
  }
}
