(ns app.symbol_solver.funcs
  (:use [app.model.protocols])
  (:use [app.model.javaparser])
  (:use [app.javaparser.navigation])
  (:use [app.utils])
  (:use [app.symbol_solver.scope]))

(defn solveNameExpr
  "given an instance of com.github.javaparser.ast.expr.NameExpr returns the declaration it refers to,
   if it can be found, nil otherwise"
  [nameExpr]
  (let [name (.getName nameExpr)]
    (solveSymbol nameExpr nil name)))

(defn solveImportStmt [importStmt]
  (let [name (importQName importStmt)]
    (solveClass (getCu importStmt) nil name)))

(defn solveSuperclass
  "Return the definition of the superclass if the class has a superclass and if can be solved"
  [classDecl]
  (let [superclass (first (.getExtends classDecl))]
    (when superclass
      (solveClass classDecl nil (getName superclass)))))

(defn getAllSuperclasses
  "Get all the superclasses of the given class declaration (recursively)"
  [classDecl]
  (let [directSuperclass (solveSuperclass classDecl)]
    (if directSuperclass
      (into [directSuperclass] (getAllSuperclasses directSuperclass))
      [])))