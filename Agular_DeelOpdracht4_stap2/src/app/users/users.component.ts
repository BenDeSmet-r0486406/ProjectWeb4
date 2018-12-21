import {Component, OnInit} from '@angular/core';
import {UserService} from './user.service';
import { timer} from 'rxjs/observable/timer';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  private userService: any ;
  persons: any[];

  constructor( userService: UserService) {
    this.userService = userService;
    this.setPersons();
  }

  onViewDidLoad() {
    this.setPersons();
  }

  setPersons(refresher?) {

    timer(0, 2500)
      .subscribe(() => {
        this.userService.getPersons()
          .subscribe(data => this.persons = data);
      });
  }
  ngOnInit(): void {
    this.setPersons();
  }

}
