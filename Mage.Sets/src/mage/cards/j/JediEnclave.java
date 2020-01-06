
package mage.cards.j;

import mage.MageObject;
import mage.abilities.ActivatedAbilityImpl;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.effects.common.search.SearchLibraryPutInPlayEffect;
import mage.abilities.mana.ColorlessManaAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.FilterCard;
import mage.filter.predicate.Predicate;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.mageobject.SubtypePredicate;
import mage.filter.predicate.mageobject.SupertypePredicate;
import mage.target.common.TargetCardInLibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Styxo
 */
public final class JediEnclave extends CardImpl {

    public JediEnclave(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.LAND},"");

        // Add {C} to your manapool.
        this.addAbility(new ColorlessManaAbility());

        // {T}, Sacrifice Jedi Enclave: Search your library for a basic Forest, Plains or Island card and put it onto the battlefield tapped. Then shuffle your library.
        this.addAbility(new JediEnclaveAbility());

    }

    public JediEnclave(final JediEnclave card) {
        super(card);
    }

    @Override
    public JediEnclave copy() {
        return new JediEnclave(this);
    }

    public static class JediEnclaveAbility extends ActivatedAbilityImpl {

        public JediEnclaveAbility(JediEnclaveAbility ability) {
            super(ability);
        }

        public JediEnclaveAbility() {
            super(Zone.BATTLEFIELD, null);
            addCost(new TapSourceCost());
            addCost(new SacrificeSourceCost());
            FilterCard filter = new FilterCard("basic Forest, Plains or Island");
            filter.add(CardType.LAND.getPredicate());
            List<Predicate<MageObject>> subtypePredicates = new ArrayList<>();
            subtypePredicates.add(new SubtypePredicate(SubType.FOREST));
            subtypePredicates.add(new SubtypePredicate(SubType.PLAINS));
            subtypePredicates.add(new SubtypePredicate(SubType.ISLAND));
            filter.add(Predicates.or(subtypePredicates));
            filter.add(new SupertypePredicate(SuperType.BASIC));
            TargetCardInLibrary target = new TargetCardInLibrary(filter);
            addEffect(new SearchLibraryPutInPlayEffect(target, true, true, Outcome.PutLandInPlay));
        }

        @Override
        public JediEnclaveAbility copy() {
            return new JediEnclaveAbility(this);
        }
    }
}
