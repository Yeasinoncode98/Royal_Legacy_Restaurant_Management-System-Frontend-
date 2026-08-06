const ApiService = {
  async get(url){
    const r=await fetch(url);
    if(!r.ok){ const e=await r.json().catch(()=>({message:"Request failed"})); throw new Error(e.message); }
    return r.json();
  },
  async post(url,body){
    const r=await fetch(url,{method:"POST",headers:{"Content-Type":"application/json"},body:JSON.stringify(body)});
    if(!r.ok){ const e=await r.json().catch(()=>({message:"Request failed"})); throw new Error(e.message); }
    return r.json();
  },
  async put(url,body){
    const r=await fetch(url,{method:"PUT",headers:{"Content-Type":"application/json"},body:JSON.stringify(body)});
    if(!r.ok){ const e=await r.json().catch(()=>({message:"Request failed"})); throw new Error(e.message); }
    return r.json();
  },
  async del(url){
    const r=await fetch(url,{method:"DELETE"});
    if(!r.ok){ const e=await r.json().catch(()=>({message:"Request failed"})); throw new Error(e.message); }
    return r.status===204?null:r.json();
  }
};
