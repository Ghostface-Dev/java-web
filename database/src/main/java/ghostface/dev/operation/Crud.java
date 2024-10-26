package ghostface.dev.operation;

import ghostface.dev.mapping.key.Key;

public interface Crud<T extends Key<?>>
        extends Insertable<T>, Selectable<T>, Upgradable<T>, Deletable<T>
{
    // Do nothing
}
