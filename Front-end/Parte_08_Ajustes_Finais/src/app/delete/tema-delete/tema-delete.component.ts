import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Topic } from 'src/app/model/Topic';
import { AlertasService } from 'src/app/service/alertas.service';
import { TemaService } from 'src/app/service/tema.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-tema-delete',
  templateUrl: './tema-delete.component.html',
  styleUrls: ['./tema-delete.component.css'],
})
export class TemaDeleteComponent implements OnInit {
  tema: Topic = new Topic();
  idTema: number;

  constructor(
    private temaService: TemaService,
    private router: Router,
    private route: ActivatedRoute,
    private alertas: AlertasService
  ) {}

  ngOnInit() {
    if (environment.token == '') {
      this.alertas.showAlertInfo('Sua sessão expirou! Faça o login novamente.');
      this.router.navigate(['/entrar']);
    }

    this.idTema = this.route.snapshot.params['id'];
    this.findByIdTema(this.idTema);
  }

  findByIdTema(id: number) {
    this.temaService.getByIdTema(id).subscribe((resp: Topic) => {
      this.tema = resp;
    });
  }

  apagar() {
    this.temaService.deleteTema(this.idTema).subscribe(() => {
      this.alertas.showAlertSuccess('Tema excluído com sucesso!');
      this.router.navigate(['/tema']);
    });
  }
}
