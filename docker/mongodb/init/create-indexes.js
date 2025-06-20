// Script para criar índices no MongoDB durante a inicialização do container
print("Iniciando a criação das collections e índices para o sistema ACAL...");

// Criar collections explicitamente antes de criar os índices
print("Criando as collections necessárias...");
db.createCollection("customer");
db.createCollection("address");
db.createCollection("places");
db.createCollection("category");
db.createCollection("link");
print("Collections criadas com sucesso!");

// Índice para o campo identityCard na coleção customer
print("Criando índice para a coleção 'customer'...");
db.customer.createIndex(
  { "identity_card": 1 },
  { name: "unique_identity_card_idx", unique: true }
);

// Índice para a coleção address
print("Criando índice para a coleção 'address'...");
db.address.createIndex(
  { "name": 1 },
  { name: "unique_name_index", unique: true }
);

// Índice para a coleção places
print("Criando índice para a coleção 'places'...");
db.places.createIndex(
  { "number": 1, "letter": 1, "address._id": 1 },
  { name: "place_unique_idx", unique: true }
);

// Índice para a coleção category
print("Criando índice para a coleção 'category'...");
db.category.createIndex(
  { "name": 1, "group": 1 },
  { name: "unique_name_per_group_idx", unique: true }
);

// Índice para a coleção link com controle de duplicação baseado no campo active
print("Criando índice para a coleção 'link' com controle de duplicação baseado no campo 'active'...");
db.link.createIndex(
  { "customer._id": 1, "place._id": 1, "category._id": 1 },
  {
    name: "unique_customer_place_category_active_idx",
    unique: true,
    partialFilterExpression: { active: true }
  }
);

print("Criação de collections e índices concluída!");
