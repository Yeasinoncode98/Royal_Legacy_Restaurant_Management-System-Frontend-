const Auth = {
  save(u){ localStorage.setItem("rl_user", JSON.stringify(u)); },
  get(){ const r=localStorage.getItem("rl_user"); return r?JSON.parse(r):null; },
  isLoggedIn(){ return !!this.get(); },
  isAdmin(){ const u=this.get(); return u&&u.role==="ADMIN"; },
  logout(){ localStorage.removeItem("rl_user"); window.location.href=root()+"index.html"; }
};

function root(){
  return window.location.pathname.includes("/pages/") ? "../" : "./";
}

function updateNav(){
  const u=Auth.get();
  const el=document.getElementById("nav-actions");
  if(!el) return;
  if(u){
    el.innerHTML=`
      <span class="nav-greeting">Hi, ${u.name.split(" ")[0]}</span>
      ${u.role==="ADMIN"?`<a href="${root()}pages/admin.html" class="btn btn-sm btn-outline">Admin</a>`:""}
      <a href="${root()}pages/cart.html" class="btn-cart-icon" id="cart-btn">
        <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><circle cx="9" cy="21" r="1"/><circle cx="20" cy="21" r="1"/><path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"/></svg>
        <span class="cart-badge" id="nav-cart-count">0</span>
      </a>
      <button onclick="Auth.logout()" class="btn btn-sm btn-outline">Logout</button>`;
  } else {
    el.innerHTML=`
      <a href="${root()}pages/login.html" class="btn btn-sm btn-outline">Login</a>
      <a href="${root()}pages/register.html" class="btn btn-sm btn-primary">Register</a>`;
  }
  updateCartCount();
}

function updateCartCount(){
  const n=Cart.getCount();
  const b=document.getElementById("nav-cart-count");
  if(b){ b.textContent=n; b.style.display=n>0?"flex":"none"; }
}

function showToast(msg, type="success"){
  document.querySelectorAll(".toast").forEach(t=>t.remove());
  const t=document.createElement("div");
  t.className=`toast toast-${type}`;
  t.textContent=msg;
  document.body.appendChild(t);
  requestAnimationFrame(()=>t.classList.add("show"));
  setTimeout(()=>{ t.classList.remove("show"); setTimeout(()=>t.remove(),300); },3000);
}
