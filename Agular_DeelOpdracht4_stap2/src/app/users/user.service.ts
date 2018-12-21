import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/observable';


@Injectable()
export class UserService {
  private url = 'http://localhost:8080/WEB4_war_exploded/Controller?action=personList';


  constructor(private http: HttpClient) {
  }


  getPersons(): Observable<any[]> {
    return this.http.get<any[]>(this.url);
  }
}
