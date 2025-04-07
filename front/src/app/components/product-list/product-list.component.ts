import { Component, OnInit } from '@angular/core';
import { Product } from '../../models/product';
import { ProductService } from '../../services/product.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];
  filterName: string = '';
  sortBy: string = '';

  constructor(
    private productService: ProductService,
    private router: Router,
    private http: HttpClient
  ) {}

  ngOnInit() {
    this.fetchProducts();
  }

  fetchProducts() {
    this.productService.getAll().subscribe(data => this.products = data);
  }

  deleteProduct(id: number) {
    if (confirm('Are you sure?')) {
      this.productService.delete(id).subscribe(() => this.fetchProducts());
    }
  }

  editProduct(id: number) {
    this.router.navigate(['/edit', id]);
  }

  createProduct() {
    this.router.navigate(['/create']);
  }

  applyFilters() {
    const params = [];
    if (this.filterName) params.push(`name=${this.filterName}`);
    if (this.sortBy) params.push(`sortBy=${this.sortBy}`);

    const query = params.length ? '?' + params.join('&') : '';
    this.http.get<Product[]>(`http://localhost:8080/api/products/filter${query}`)
      .subscribe(data => this.products = data);
  }
}
