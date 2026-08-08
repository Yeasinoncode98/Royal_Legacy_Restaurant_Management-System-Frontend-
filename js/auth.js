// const Auth = {
//   save(u){ localStorage.setItem("rl_user", JSON.stringify(u)); },
//   get(){ const r=localStorage.getItem("rl_user"); return r?JSON.parse(r):null; },
//   isLoggedIn(){ return !!this.get(); },
//   isAdmin(){ const u=this.get(); return u&&u.role==="ADMIN"; },
//   logout(){ localStorage.removeItem("rl_user"); window.location.href=root()+"index.html"; }
// };

// function root(){
//   return window.location.pathname.includes("/pages/") ? "../" : "./";
// }

// function updateNav(){
//   const u=Auth.get();
//   const el=document.getElementById("nav-actions");
//   if(!el) return;
//   if(u){
//     el.innerHTML=`
//       <span class="nav-greeting">Hi, ${u.name.split(" ")[0]}</span>
//       ${u.role==="ADMIN"?`<a href="${root()}pages/admin.html" class="btn btn-sm btn-outline">Admin</a>`:""}
//       <a href="${root()}pages/cart.html" class="btn-cart-icon" id="cart-btn">
//         <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><circle cx="9" cy="21" r="1"/><circle cx="20" cy="21" r="1"/><path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"/></svg>
//         <span class="cart-badge" id="nav-cart-count">0</span>
//       </a>
//       <button onclick="Auth.logout()" class="btn btn-sm btn-outline">Logout</button>`;
//   } else {
//     el.innerHTML=`
//       <a href="${root()}pages/login.html" class="btn btn-sm btn-outline">Login</a>
//       <a href="${root()}pages/register.html" class="btn btn-sm btn-primary">Register</a>`;
//   }
//   updateCartCount();
// }

// function updateCartCount(){
//   const n=Cart.getCount();
//   const b=document.getElementById("nav-cart-count");
//   if(b){ b.textContent=n; b.style.display=n>0?"flex":"none"; }
// }

// function showToast(msg, type="success"){
//   document.querySelectorAll(".toast").forEach(t=>t.remove());
//   const t=document.createElement("div");
//   t.className=`toast toast-${type}`;
//   t.textContent=msg;
//   document.body.appendChild(t);
//   requestAnimationFrame(()=>t.classList.add("show"));
//   setTimeout(()=>{ t.classList.remove("show"); setTimeout(()=>t.remove(),300); },3000);
// }

// 2...................

const Auth = {
  save(u) {
    localStorage.setItem("rl_user", JSON.stringify(u));
  },
  get() {
    const r = localStorage.getItem("rl_user");
    return r ? JSON.parse(r) : null;
  },
  isLoggedIn() {
    return !!this.get();
  },
  isAdmin() {
    const u = this.get();
    return u && u.role === "ADMIN";
  },
  logout() {
    localStorage.removeItem("rl_user");
    window.location.href = root() + "index.html";
  },
};

const Notifications = {
  KEY: "rl_notifications",
  getAll() {
    const r = localStorage.getItem(this.KEY);
    return r ? JSON.parse(r) : [];
  },
  save(list) {
    localStorage.setItem(this.KEY, JSON.stringify(list));
    updateNotifCount();
  },
  add(msg) {
    const list = this.getAll();
    list.unshift({
      id: Date.now(),
      msg,
      read: false,
      time: new Date().toLocaleString(),
    });
    this.save(list);
  },
  markAllRead() {
    const list = this.getAll().map((n) => ({ ...n, read: true }));
    this.save(list);
  },
  clear() {
    localStorage.removeItem(this.KEY);
    updateNotifCount();
  },
  getUnreadCount() {
    return this.getAll().filter((n) => !n.read).length;
  },
};

function root() {
  return window.location.pathname.includes("/pages/") ? "../" : "./";
}

function updateNav() {
  const u = Auth.get();
  const el = document.getElementById("nav-actions");
  if (!el) return;
  if (u) {
    el.innerHTML = `
      <span class="nav-greeting">Hi, ${u.name.split(" ")[0]}</span>
      ${u.role === "ADMIN" ? `<a href="${root()}pages/admin.html" class="btn btn-sm btn-outline">Admin</a>` : ""}
      <div style="position:relative">
<button id="notif-btn" onclick="toggleNotifDropdown()" style="position:relative;background:none;border:none;cursor:pointer;padding:6px;transition:transform 0.2s" onmouseover="this.style.transform='scale(1.1)'" onmouseout="this.style.transform='scale(1)'">
  <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="#f5a623" stroke-width="2"><path d="M18 8a6 6 0 0 0-12 0c0 7-3 9-3 9h18s-3-2-3-9"/><path d="M13.73 21a2 2 0 0 1-3.46 0"/></svg>
  <span id="notif-dot" style="display:none;position:absolute;top:2px;right:2px;width:9px;height:9px;background:#ff3b30;border-radius:50%;border:1.5px solid #1a1a1a"></span>
</button>
        <div id="notif-dropdown" style="display:none;position:absolute;right:0;top:38px;width:280px;background:#fff;color:#222;border-radius:10px;box-shadow:0 8px 24px rgba(0,0,0,0.15);z-index:999;max-height:320px;overflow-y:auto">
          <div id="notif-list" style="padding:8px"></div>
        </div>
      </div>
      <a href="${root()}pages/cart.html" class="btn-cart-icon" id="cart-btn">
        <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><circle cx="9" cy="21" r="1"/><circle cx="20" cy="21" r="1"/><path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"/></svg>
        <span class="cart-badge" id="nav-cart-count">0</span>
      </a>
      <button onclick="Auth.logout()" class="btn btn-sm btn-outline">Logout</button>`;
  } else {
    el.innerHTML = `
      <a href="${root()}pages/login.html" class="btn btn-sm btn-outline">Login</a>
      <a href="${root()}pages/register.html" class="btn btn-sm btn-primary">Register</a>`;
  }
  updateCartCount();
  updateNotifCount();
}

function updateCartCount() {
  const n = Cart.getCount();
  const b = document.getElementById("nav-cart-count");
  if (b) {
    b.textContent = n;
    b.style.display = n > 0 ? "flex" : "none";
  }
}

function updateNotifCount() {
  const dot = document.getElementById("notif-dot");
  if (!dot) return;
  const unread = Notifications.getUnreadCount();
  dot.style.display = unread > 0 ? "block" : "none";
}

function toggleNotifDropdown() {
  const dd = document.getElementById("notif-dropdown");
  if (!dd) return;
  const opening = dd.style.display === "none";
  dd.style.display = opening ? "block" : "none";
  if (opening) {
    renderNotifList();
    Notifications.markAllRead();
  }
}

function renderNotifList() {
  const list = Notifications.getAll();
  const el = document.getElementById("notif-list");
  if (!el) return;
  if (list.length === 0) {
    el.innerHTML = `<div style="padding:12px;font-size:0.85rem;color:#888">No notifications yet.</div>`;
    return;
  }
  el.innerHTML = list
    .map(
      (n) => `
    <div style="padding:10px;border-bottom:1px solid #eee;font-size:0.85rem">
      <div>${n.msg}</div>
      <div style="font-size:0.72rem;color:#999;margin-top:3px">${n.time}</div>
    </div>`,
    )
    .join("");
}

document.addEventListener("click", (e) => {
  const dd = document.getElementById("notif-dropdown");
  const btn = document.getElementById("notif-btn");
  if (!dd || dd.style.display === "none") return;
  if (!dd.contains(e.target) && e.target !== btn && !btn?.contains(e.target)) {
    dd.style.display = "none";
  }
});

function showToast(msg, type = "success") {
  document.querySelectorAll(".toast").forEach((t) => t.remove());
  const t = document.createElement("div");
  t.className = `toast toast-${type}`;
  t.textContent = msg;
  document.body.appendChild(t);
  requestAnimationFrame(() => t.classList.add("show"));
  setTimeout(() => {
    t.classList.remove("show");
    setTimeout(() => t.remove(), 300);
  }, 3000);
}
