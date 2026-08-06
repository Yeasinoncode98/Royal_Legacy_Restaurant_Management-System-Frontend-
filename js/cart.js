const Cart = {
  KEY: "rl_cart",
  getAll(){ const r=localStorage.getItem(this.KEY); return r?JSON.parse(r):[]; },
  save(items){ localStorage.setItem(this.KEY,JSON.stringify(items)); updateCartCount(); },
  add(food){
    const items=this.getAll();
    const ex=items.find(i=>i.id===food.id);
    if(ex) ex.quantity+=1; else items.push({...food,quantity:1});
    this.save(items);
    showToast(`${food.name} added to cart!`);
  },
  remove(id){ this.save(this.getAll().filter(i=>i.id!==id)); },
  updateQty(id,qty){
    if(qty<=0){ this.remove(id); return; }
    const items=this.getAll();
    const i=items.find(i=>i.id===id);
    if(i){ i.quantity=qty; this.save(items); }
  },
  clear(){ localStorage.removeItem(this.KEY); updateCartCount(); },
  getTotal(){ return this.getAll().reduce((s,i)=>s+i.price*i.quantity,0); },
  getCount(){ return this.getAll().reduce((s,i)=>s+i.quantity,0); }
};
