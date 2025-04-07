import { Component, OnInit } from '@angular/core';
import { Product } from '../../models/product';
import { ProductService } from '../../services/product.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit {
  product: Product = {
    name: '',
    description: '',
    price: 0,
    quantity: 0,
    categoryPath: '',
    available: false
  };
  
  isEdit: boolean = false;

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.params['id'];
    if (id) {
      this.isEdit = true;
      this.productService.getById(+id).subscribe(data => this.product = data);
    }
  }

  saveProduct() {
    if (this.isEdit && this.product.id != null) {
      this.productService.update(this.product.id, this.product).subscribe(() => this.router.navigate(['/list']));
    } else {
      this.productService.create(this.product).subscribe(() => this.router.navigate(['/list']));
    }
  }
}
